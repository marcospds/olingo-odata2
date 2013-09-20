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
package org.apache.olingo.odata2.processor.core.jpa.access.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.apache.olingo.odata2.api.edm.EdmSimpleType;
import org.apache.olingo.odata2.api.edm.EdmSimpleTypeKind;
import org.apache.olingo.odata2.api.edm.EdmType;
import org.apache.olingo.odata2.processor.api.jpa.exception.ODataJPAModelException;
import org.apache.olingo.odata2.processor.api.jpa.exception.ODataJPARuntimeException;

public class EdmTypeConvertor {

  public static Class<?> convertToJavaType(final EdmType edmType) throws ODataJPAModelException,
      ODataJPARuntimeException {
    if (edmType instanceof EdmSimpleType) {
      EdmSimpleType edmSimpleType = (EdmSimpleType) edmType;
      if (edmSimpleType == EdmSimpleTypeKind.String.getEdmSimpleTypeInstance()) {
        return String.class;
      } else if (edmSimpleType == EdmSimpleTypeKind.Int64.getEdmSimpleTypeInstance()) {
        return Long.TYPE;
      } else if (edmSimpleType == EdmSimpleTypeKind.Int16.getEdmSimpleTypeInstance()) {
        return Short.TYPE;
      } else if (edmSimpleType == EdmSimpleTypeKind.Int32.getEdmSimpleTypeInstance()) {
        return Integer.TYPE;
      } else if (edmSimpleType == EdmSimpleTypeKind.Double.getEdmSimpleTypeInstance()) {
        return Double.TYPE;
      } else if (edmSimpleType == EdmSimpleTypeKind.Single.getEdmSimpleTypeInstance()) {
        return Float.TYPE;
      } else if (edmSimpleType == EdmSimpleTypeKind.Decimal.getEdmSimpleTypeInstance()) {
        return BigDecimal.class;
      } else if (edmSimpleType == EdmSimpleTypeKind.Binary.getEdmSimpleTypeInstance()) {
        return byte[].class;
      } else if (edmSimpleType == EdmSimpleTypeKind.Byte.getEdmSimpleTypeInstance()) {
        return Byte.TYPE;
      } else if (edmSimpleType == EdmSimpleTypeKind.Boolean.getEdmSimpleTypeInstance()) {
        return Boolean.TYPE;
      } else if (edmSimpleType == EdmSimpleTypeKind.DateTime.getEdmSimpleTypeInstance()) {
        return Date.class;
      } else if (edmSimpleType == EdmSimpleTypeKind.DateTimeOffset.getEdmSimpleTypeInstance()) {
        return Calendar.class;
      } else if (edmSimpleType == EdmSimpleTypeKind.Guid.getEdmSimpleTypeInstance()) {
        return UUID.class;
      }
    }
    throw ODataJPAModelException.throwException(ODataJPAModelException.TYPE_NOT_SUPPORTED
        .addContent(edmType.toString()), null);
  }
}
