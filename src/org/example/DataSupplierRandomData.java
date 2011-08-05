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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DataSupplierRandomData implements DataSupplier {
  /**
   * Generates a random record for every minute between the from and to dates.
   *
   * @param from
   * @param to
   * @param dataType
   * @return
   */
  public List<RRDRecord> findRecords(Date from, Date to, String dataType) {
    Random random = new Random();
    long toTime = to.getTime();
    long fromTime = from.getTime();
    long elapsedTime = toTime - fromTime;

    int numberOfRecords = (int) TimeUnit.MILLISECONDS.toMinutes(elapsedTime);

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(from);

    List<RRDRecord> rrdRecords = new ArrayList<RRDRecord>(numberOfRecords);
    while (calendar.getTime().before(to)){
      rrdRecords.add(new RRDRecord(calendar.getTime(), random.nextFloat()));
      calendar.add(Calendar.MINUTE, 1);
    }
    return rrdRecords;
  }
}
