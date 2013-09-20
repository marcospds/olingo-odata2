/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 ******************************************************************************/
package org.apache.olingo.odata2.processor.core.jpa.access.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Method;

import org.apache.olingo.odata2.processor.api.jpa.exception.ODataJPARuntimeException;
import org.apache.olingo.odata2.processor.core.jpa.common.ODataJPATestConstants;
import org.junit.Test;

public class JPAEntityParserTestForStaticMethods {

  @Test
  public void testToStringDefault() {

    Character[] input = new Character[] { 'A', 'B' };
    assertEquals("AB", JPAEntityParser.toString(input));

  }

  @Test
  public void testToStringNull() {
    Character[] input = null;
    assertNull(JPAEntityParser.toString(input));
  }

  @Test
  public void testToStringPartialNull() {
    Character[] input = new Character[] { 'A', null };
    assertEquals("A", JPAEntityParser.toString(input));
  }

  @Test
  public void testToCharacterArrayDefault() {
    String input = new String("AB");
    Character[] ch = JPAEntityParser.toCharacterArray(input);

    assertEquals(2, ch.length);
    assertTrue(ch[0].equals('A'));
    assertTrue(ch[1].equals('B'));
  }

  @Test
  public void testToCharacterArrayNull() {
    String input = null;
    Character[] ch = JPAEntityParser.toCharacterArray(input);

    assertNull(ch);
  }

  @Test
  public void testGetPropertyCharacter() {
    try {
      Method method = JPAEntityParserTestForStaticMethods.class.getMethod("getCharacter", (Class<?>[]) null);
      String output = (String) JPAEntityParser.getProperty(method, this);
      assertEquals("A", output);

    } catch (NoSuchMethodException e) {
      fail(ODataJPATestConstants.EXCEPTION_MSG_PART_1 + e.getMessage() + ODataJPATestConstants.EXCEPTION_MSG_PART_2);
    } catch (SecurityException e) {
      fail(ODataJPATestConstants.EXCEPTION_MSG_PART_1 + e.getMessage() + ODataJPATestConstants.EXCEPTION_MSG_PART_2);
    } catch (ODataJPARuntimeException e) {
      fail(ODataJPATestConstants.EXCEPTION_MSG_PART_1 + e.getMessage() + ODataJPATestConstants.EXCEPTION_MSG_PART_2);
    }
  }

  @Test
  public void testGetPropertyCharacterNull() {
    try {
      Method method = JPAEntityParserTestForStaticMethods.class.getMethod("getCharacterNull", (Class<?>[]) null);
      String output = (String) JPAEntityParser.getProperty(method, this);
      assertNull(output);

    } catch (NoSuchMethodException e) {
      fail(ODataJPATestConstants.EXCEPTION_MSG_PART_1 + e.getMessage() + ODataJPATestConstants.EXCEPTION_MSG_PART_2);
    } catch (SecurityException e) {
      fail(ODataJPATestConstants.EXCEPTION_MSG_PART_1 + e.getMessage() + ODataJPATestConstants.EXCEPTION_MSG_PART_2);
    } catch (ODataJPARuntimeException e) {
      fail(ODataJPATestConstants.EXCEPTION_MSG_PART_1 + e.getMessage() + ODataJPATestConstants.EXCEPTION_MSG_PART_2);
    }
  }

  @Test
  public void testGetPropertyCharacterArray() {
    try {
      Method method = JPAEntityParserTestForStaticMethods.class.getMethod("getCharacterArray", (Class<?>[]) null);
      String output = (String) JPAEntityParser.getProperty(method, this);
      assertEquals("AB", output);

    } catch (NoSuchMethodException e) {
      fail(ODataJPATestConstants.EXCEPTION_MSG_PART_1 + e.getMessage() + ODataJPATestConstants.EXCEPTION_MSG_PART_2);
    } catch (SecurityException e) {
      fail(ODataJPATestConstants.EXCEPTION_MSG_PART_1 + e.getMessage() + ODataJPATestConstants.EXCEPTION_MSG_PART_2);
    } catch (ODataJPARuntimeException e) {
      fail(ODataJPATestConstants.EXCEPTION_MSG_PART_1 + e.getMessage() + ODataJPATestConstants.EXCEPTION_MSG_PART_2);
    }
  }

  @Test
  public void testGetPropertyCharacterArrayNull() {
    try {
      Method method = JPAEntityParserTestForStaticMethods.class.getMethod("getCharacterArrayNull", (Class<?>[]) null);
      String output = (String) JPAEntityParser.getProperty(method, this);
      assertNull(output);

    } catch (NoSuchMethodException e) {
      fail(ODataJPATestConstants.EXCEPTION_MSG_PART_1 + e.getMessage() + ODataJPATestConstants.EXCEPTION_MSG_PART_2);
    } catch (SecurityException e) {
      fail(ODataJPATestConstants.EXCEPTION_MSG_PART_1 + e.getMessage() + ODataJPATestConstants.EXCEPTION_MSG_PART_2);
    } catch (ODataJPARuntimeException e) {
      fail(ODataJPATestConstants.EXCEPTION_MSG_PART_1 + e.getMessage() + ODataJPATestConstants.EXCEPTION_MSG_PART_2);
    }
  }

  @Test
  public void testGetPropertyChar() {
    try {
      Method method = JPAEntityParserTestForStaticMethods.class.getMethod("getChar", (Class<?>[]) null);
      String output = (String) JPAEntityParser.getProperty(method, this);
      assertEquals("A", output);

    } catch (NoSuchMethodException e) {
      fail(ODataJPATestConstants.EXCEPTION_MSG_PART_1 + e.getMessage() + ODataJPATestConstants.EXCEPTION_MSG_PART_2);
    } catch (SecurityException e) {
      fail(ODataJPATestConstants.EXCEPTION_MSG_PART_1 + e.getMessage() + ODataJPATestConstants.EXCEPTION_MSG_PART_2);
    } catch (ODataJPARuntimeException e) {
      fail(ODataJPATestConstants.EXCEPTION_MSG_PART_1 + e.getMessage() + ODataJPATestConstants.EXCEPTION_MSG_PART_2);
    }
  }

  @Test
  public void testGetPropertyCharNull() {
    try {
      Method method = JPAEntityParserTestForStaticMethods.class.getMethod("getCharNull", (Class<?>[]) null);
      String output = (String) JPAEntityParser.getProperty(method, this);
      assertNull(output);

    } catch (NoSuchMethodException e) {
      fail(ODataJPATestConstants.EXCEPTION_MSG_PART_1 + e.getMessage() + ODataJPATestConstants.EXCEPTION_MSG_PART_2);
    } catch (SecurityException e) {
      fail(ODataJPATestConstants.EXCEPTION_MSG_PART_1 + e.getMessage() + ODataJPATestConstants.EXCEPTION_MSG_PART_2);
    } catch (ODataJPARuntimeException e) {
      fail(ODataJPATestConstants.EXCEPTION_MSG_PART_1 + e.getMessage() + ODataJPATestConstants.EXCEPTION_MSG_PART_2);
    }
  }

  @Test
  public void testGetPropertyCharArray() {
    try {
      Method method = JPAEntityParserTestForStaticMethods.class.getMethod("getCharArray", (Class<?>[]) null);
      String output = (String) JPAEntityParser.getProperty(method, this);
      assertEquals("AB", output);

    } catch (NoSuchMethodException e) {
      fail(ODataJPATestConstants.EXCEPTION_MSG_PART_1 + e.getMessage() + ODataJPATestConstants.EXCEPTION_MSG_PART_2);
    } catch (SecurityException e) {
      fail(ODataJPATestConstants.EXCEPTION_MSG_PART_1 + e.getMessage() + ODataJPATestConstants.EXCEPTION_MSG_PART_2);
    } catch (ODataJPARuntimeException e) {
      fail(ODataJPATestConstants.EXCEPTION_MSG_PART_1 + e.getMessage() + ODataJPATestConstants.EXCEPTION_MSG_PART_2);
    }
  }

  @Test
  public void testGetPropertyCharArrayNull() {
    try {
      Method method = JPAEntityParserTestForStaticMethods.class.getMethod("getCharArrayNull", (Class<?>[]) null);
      String output = (String) JPAEntityParser.getProperty(method, this);
      assertNull(output);

    } catch (NoSuchMethodException e) {
      fail(ODataJPATestConstants.EXCEPTION_MSG_PART_1 + e.getMessage() + ODataJPATestConstants.EXCEPTION_MSG_PART_2);
    } catch (SecurityException e) {
      fail(ODataJPATestConstants.EXCEPTION_MSG_PART_1 + e.getMessage() + ODataJPATestConstants.EXCEPTION_MSG_PART_2);
    } catch (ODataJPARuntimeException e) {
      fail(ODataJPATestConstants.EXCEPTION_MSG_PART_1 + e.getMessage() + ODataJPATestConstants.EXCEPTION_MSG_PART_2);
    }
  }

  @Test
  public void testGetPropertyCharArrayValueNull() {
    try {
      Method method = JPAEntityParserTestForStaticMethods.class.getMethod("getCharArrayValueNull", (Class<?>[]) null);
      String output = (String) JPAEntityParser.getProperty(method, this);
      assertEquals("A\u0000", output);

    } catch (NoSuchMethodException e) {
      fail(ODataJPATestConstants.EXCEPTION_MSG_PART_1 + e.getMessage() + ODataJPATestConstants.EXCEPTION_MSG_PART_2);
    } catch (SecurityException e) {
      fail(ODataJPATestConstants.EXCEPTION_MSG_PART_1 + e.getMessage() + ODataJPATestConstants.EXCEPTION_MSG_PART_2);
    } catch (ODataJPARuntimeException e) {
      fail(ODataJPATestConstants.EXCEPTION_MSG_PART_1 + e.getMessage() + ODataJPATestConstants.EXCEPTION_MSG_PART_2);
    }
  }

  public Character getCharacter() {
    return new Character('A');
  }

  public Character getCharacterNull() {
    return null;
  }

  public Character[] getCharacterArray() {
    return new Character[] { 'A', 'B' };
  }

  public Character[] getCharacterArrayNull() {
    return null;
  }

  public char getChar() {
    return 'A';
  }

  public char getCharNull() {
    return '\u0000';
  }

  public char[] getCharArray() {
    return new char[] { 'A', 'B' };
  }

  public char[] getCharArrayNull() {
    return null;
  }

  public char[] getCharArrayValueNull() {
    return new char[] { 'A', '\u0000' };
  }
}
