/*
 * This program is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License, version 2.1 as published by the Free Software
 * Foundation.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * program; if not, you can obtain a copy at http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
 * or from the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * Copyright (c) 2009 Pentaho Corporation.  All rights reserved.
 */
package org.pentaho.metadata.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.pentaho.metadata.query.model.util.CsvDataReader;

public class CsvDataReaderTest {

  @Test
  public void testLoadData() throws Exception {
    CsvDataReader reader = new CsvDataReader("test/solution/system/metadata/csvfiles/anotherexample.csv", true, ",", "\"", 5);
    List<List<String>> data = reader.loadData();
    List<String> originalHeader = new ArrayList<String>();
    originalHeader.add("Task");
    originalHeader.add("Est'd Time");
    originalHeader.add("Time Left");
    originalHeader.add("Time Spent");
    originalHeader.add("Time Left");
    Assert.assertTrue(compare(reader.getHeader(), originalHeader ));
    
  }
  
  @Test
  public void testGetDataByColumnName() throws Exception {
    List<String> originalData = new ArrayList<String>();
    originalData.add("3.5");
    originalData.add("8");
    originalData.add("13");    
    CsvDataReader reader = new CsvDataReader("test/solution/system/metadata/csvfiles/anotherexample.csv", true, ",", "\"", 5);
    reader.loadData();
    List<String> data = reader.getColumnData(1);
    Assert.assertTrue(compare(data,originalData));
  }
  
  @Test
  public void testGetDataByColumnNumber() throws Exception {
    List<String> originalData = new ArrayList<String>();
    originalData.add("3.5");
    originalData.add("4");
    originalData.add("0");
    CsvDataReader reader = new CsvDataReader("test/solution/system/metadata/csvfiles/anotherexample.csv", true, ",", "\"", 5);
    reader.loadData();
    List<String> data = reader.getColumnData(2);
    Assert.assertTrue(compare(data, originalData));    
  }

  @Test
  public void testCheckForIndexOutOfBoundException() throws Exception {
    List<String> data = null;
    try {
    CsvDataReader reader = new CsvDataReader("test/solution/system/metadata/csvfiles/csv_various_types.csv", true, ",", "\"", 5);
    reader.loadData();
    data = reader.getColumnData(2);
    Assert.assertTrue(data != null);
    } catch(IndexOutOfBoundsException ex) {
      Assert.assertFalse(data != null);
    }
  }
  
  private boolean compare(List<String> list1, List<String> list2) {
    int i=0;
    for(String data : list1) {
      // System.out.println("comparing " + data + " to " + list2.get(i));
      if (data.equals(list2.get(i))) {
        i++;
      } else {
        return false;
      }
    }
    return true;
  }
}
