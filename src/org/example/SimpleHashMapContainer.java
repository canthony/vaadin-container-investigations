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

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.PropertysetItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * org.example.SimpleHashMapContainer, created on 05/08/11 09:39 <p>
 * @author Charles
 */
public class SimpleHashMapContainer implements Container, Container.Indexed {
  private Map<Object, Item> idToItem = new HashMap<Object, Item>();
  private List<Object> itemIds = new ArrayList<Object>();

  public SimpleHashMapContainer() {
    // No-OP -
  }

  public SimpleHashMapContainer(int initialCapacity) {
    idToItem = new HashMap<Object, Item>(initialCapacity);
    itemIds = new ArrayList<Object>(initialCapacity);
  }

  public Item getItem(Object itemId) {
    return idToItem.get(itemId);
  }

  public Collection<?> getContainerPropertyIds() {
    throw new UnsupportedOperationException("getContainerPropertyIds is not supported");
  }

  public Collection<?> getItemIds() {
    return idToItem.keySet();
  }

  public Property getContainerProperty(Object itemId, Object propertyId) {
    Item item = getItem(itemId);
    return item == null ? null : item.getItemProperty(propertyId);
  }

  public Class<?> getType(Object propertyId) {
    throw new UnsupportedOperationException("getType is not supported");
  }

  public int size() {
    return idToItem.size();
  }

  public boolean containsId(Object itemId) {
    return idToItem.containsKey(itemId);
  }

  public Item addItem(Object itemId) throws UnsupportedOperationException {
    Item item = new PropertysetItem();
    idToItem.put(itemId, item);
    itemIds.add(itemId);
    return item;
  }

  public int indexOfId(Object itemId) {
    return itemIds.indexOf(itemId);
  }

  public Object getIdByIndex(int index) {
    return itemIds.get(index);
  }


  public Object nextItemId(Object itemId) {
    int ix = indexOfId(itemId);
    int lastIx = indexOfId(lastItemId());

    return ix == -1 || ix == lastIx ? null : itemIds.get(ix + 1);
  }

  public Object prevItemId(Object itemId) {
    int ix = indexOfId(itemId);
    int firstIx = indexOfId(firstItemId());

    return ix == -1 || ix == firstIx ? null : itemIds.get(ix - 1);
  }

  public Object firstItemId() {
    return itemIds.isEmpty() ? null : itemIds.get(0);
  }

  public Object lastItemId() {
    return itemIds.isEmpty() ? null : itemIds.get(itemIds.size());
  }

  public boolean isFirstId(Object itemId) {
    Object first = firstItemId();
    return first == itemId || first != null && itemId != null && firstItemId().equals(first);
  }

  public boolean isLastId(Object itemId) {
    Object last = lastItemId();
    return last == itemId || last != null && itemId != null && firstItemId().equals(last);
  }

  public Object addItem() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("addItem without an id is not supported");
  }

  public boolean removeItem(Object itemId) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  public boolean addContainerProperty(Object propertyId, Class<?> type, Object defaultValue) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  public boolean removeContainerProperty(Object propertyId) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  public boolean removeAllItems() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  public Object addItemAt(int index) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }


  public Item addItemAt(int index, Object newItemId) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  public Object addItemAfter(Object previousItemId) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  public Item addItemAfter(Object previousItemId, Object newItemId) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

}
