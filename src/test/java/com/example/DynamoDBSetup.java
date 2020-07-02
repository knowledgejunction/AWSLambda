package com.example;

import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeDefinition;
import software.amazon.awssdk.services.dynamodb.model.BillingMode;
import software.amazon.awssdk.services.dynamodb.model.KeySchemaElement;
import software.amazon.awssdk.services.dynamodb.model.KeyType;
import software.amazon.awssdk.services.dynamodb.model.ResourceNotFoundException;
import software.amazon.awssdk.services.dynamodb.model.ScalarAttributeType;
import software.amazon.awssdk.services.dynamodb.model.TableStatus;
import software.amazon.awssdk.services.dynamodb.model.CreateTableRequest.Builder;
public class DynamoDBSetup {
   private final Logger log;
   private final String tableName;
   private final DynamoDbClient dynamoDbClient;

   public  void createTables() {
      this.deleteTables();
      this.dynamoDbClient.createTable(new Consumer() {
         public void accept(Object var1) {
            this.accept((Builder)var1);
         }

         public final void accept(Builder it) {
            it.tableName(DynamoDBSetup.this.tableName);
            it.billingMode(BillingMode.PAY_PER_REQUEST);
            it.attributeDefinitions(new AttributeDefinition[]{AttributeDefinition.builder().attributeName("id").attributeType(ScalarAttributeType.S).build()});
            it.keySchema(new KeySchemaElement[]{KeySchemaElement.builder().attributeName("id").keyType(KeyType.HASH).build()});
         }
      });
   }

   public void deleteTables() {
      try {
         this.log.info("Deleting table " + this.tableName);
         this.dynamoDbClient.deleteTable(new Consumer() {
            public void accept(Object var1) {
               this.accept((software.amazon.awssdk.services.dynamodb.model.DeleteTableRequest.Builder)var1);
            }

            public final void accept(software.amazon.awssdk.services.dynamodb.model.DeleteTableRequest.Builder it) {
               it.tableName(DynamoDBSetup.this.tableName);
            }
         });

         while(this.getTableStatus(this.tableName) == TableStatus.DELETING) {
            Thread.sleep(500L);
         }
      } catch (ResourceNotFoundException var2) {
      } catch (InterruptedException e) {
         e.printStackTrace();
      }

   }

   private  void waitUntilTableActive(String tableName) {
      try {
         while(this.getTableStatus(tableName) != TableStatus.ACTIVE) {
            Thread.sleep(500L);
         }

         this.log.info("Table " + tableName + " is active");
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
   }

   private  TableStatus getTableStatus(final String tableName) {
      TableStatus tableStatus = this.dynamoDbClient.describeTable(new Consumer() {
         public void accept(Object var1) {
            this.accept((software.amazon.awssdk.services.dynamodb.model.DescribeTableRequest.Builder)var1);
         }

         public final void accept(software.amazon.awssdk.services.dynamodb.model.DescribeTableRequest.Builder it) {
            it.tableName(tableName);
         }
      }).table().tableStatus();
      if (tableStatus == null) {
         throw new NullPointerException();
      }

      return tableStatus;
   }

   public DynamoDBSetup(DynamoDbClient dynamoDbClient) {
      super();
      this.dynamoDbClient = dynamoDbClient;
      this.log = LoggerFactory.getLogger(DynamoDBSetup.class);
      this.tableName = "greetings";
   }
}
