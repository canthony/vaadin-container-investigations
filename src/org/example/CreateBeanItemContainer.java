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

import com.vaadin.data.util.BeanItemContainer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * org.example.CreateBeanItemContainer, created on 05/08/11 15:18 <p>
 * @author Charles
 */
public class CreateBeanItemContainer {
  public static void main(String[] args) {
    RawRRDData[] webserviceResults = new RawRRDData[]{new RawRRDData(0,1), new RawRRDData(100, 2)};

    // Transform RawRRDData to a list of RRDBeans
    List<RRDBean> beans = new ArrayList<RRDBean>(webserviceResults.length);
    for (int i = 0; i < webserviceResults.length; i++) {
      RawRRDData webserviceResult = webserviceResults[i];
      beans.add(new RRDBean(new Date(webserviceResult.getRTime()), webserviceResult.getRValue()));
    }
    // Create a container from the beans
    BeanItemContainer<RRDBean> container = new BeanItemContainer<RRDBean>(RRDBean.class, beans);

    /* "time" and "value" matche the bean properties on RRDBean - following the standard JavaBean specification
    Timeline timeline = new Timeline("testing");
    timeline.addGraphDataSource(container);
    timeline.setGraphTimestampPropertyId(container, "time");
    timeline.setGraphValuePropertyId(container, "value");
     */

  }
}
