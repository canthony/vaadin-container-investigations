/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.example;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class IndexedContainerExample {
  private static final String TIMESTAMP = "timestamp";
  private static final String VALUE = "value";

  public static void main(String[] args) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(2010, Calendar.JULY, 31);
    Date from = calendar.getTime();
    calendar.set(2011, Calendar.JULY, 31);
    Date to = calendar.getTime();

    DataSupplier supplier = new DataSupplierRandomData();

    long startTime = System.nanoTime();
    List<RRDRecord> records = supplier.findRecords(from, to, "bandwidth");
    long stopTime = System.nanoTime();
    System.out.println("Created " + records.size() + " records in " + TimeUnit.NANOSECONDS.toMillis(stopTime - startTime) + "ms");

      createIndexedContainer(records);
//    createHashMapContainer(records);
//
//    createBeanItemContainer(records);


  }

  private static Container.Indexed createHashMapContainer(List<RRDRecord> records) {
    long startTime = System.nanoTime();
    SimpleHashMapContainer container = new SimpleHashMapContainer(records.size());
    for (RRDRecord record : records) {
      Date timeStamp = record.getTimestamp();

      PropertysetItem item = (PropertysetItem) container.addItem(timeStamp);
      item.addItemProperty(TIMESTAMP, new ObjectProperty(timeStamp, Date.class, true));
      item.addItemProperty(VALUE, new ObjectProperty(record.getValue(), Float.class, true));
    }

    long stopTime = System.nanoTime();
    System.out.println("SimpleHashMapContainer has " + container.size() + " items, taking " + TimeUnit.NANOSECONDS.toMillis(stopTime - startTime) + "ms");
    return container;
  }

  private static Container.Indexed createBeanItemContainer(List<RRDRecord> records){
    long startTime = System.nanoTime();
    BeanItemContainer beanContainer = new BeanItemContainer(RRDRecord.class, records);
    long stopTime = System.nanoTime();
    System.out.println("BeanContainer has " + beanContainer.size() + " items, taking " + TimeUnit.NANOSECONDS.toMillis(stopTime - startTime) + "ms");

    return beanContainer;
  }

  private static Container.Indexed createIndexedContainer(List<RRDRecord> records) {
    long startTime = System.nanoTime();
    IndexedContainer container = new IndexedContainer();
    container.addContainerProperty(TIMESTAMP, Date.class, null);
    container.addContainerProperty(VALUE, Float.class, null);
    for (RRDRecord record : records) {
      Date timeStamp = record.getTimestamp();

      Item item = container.addItem(timeStamp);
      item.getItemProperty(TIMESTAMP).setValue(timeStamp);
      item.getItemProperty(VALUE).setValue(record.getValue());
    }
    long stopTime = System.nanoTime();
    System.out.println("IndexedContainer has " + container.size() + " items, taking " + TimeUnit.NANOSECONDS.toMillis(stopTime - startTime) + "ms");
    return container;
  }
}
