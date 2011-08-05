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

/**
 * org.example.RawRDData, created on 05/08/11 15:07 <p>
 * @author Charles
 */
public class RawRRDData implements java.io.Serializable {
  private java.lang.String errMsg;

  private long RTime;

  private double RValue;

  public RawRRDData(long RTime, double RValue) {
    this.RTime = RTime;
    this.RValue = RValue;
  }

  public long getRTime() { return RTime; }

  public double getRValue() { return RValue; }
}