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
package org.apache.olingo.odata2.processor.core.jpa.jpql;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.metamodel.Metamodel;

import org.apache.olingo.odata2.api.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.edm.EdmEntityType;
import org.apache.olingo.odata2.api.edm.EdmException;
import org.apache.olingo.odata2.api.edm.EdmMapping;
import org.apache.olingo.odata2.api.edm.EdmProperty;
import org.apache.olingo.odata2.api.edm.EdmSimpleType;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.uri.KeyPredicate;
import org.apache.olingo.odata2.api.uri.NavigationSegment;
import org.apache.olingo.odata2.api.uri.expression.OrderByExpression;
import org.apache.olingo.odata2.api.uri.info.GetEntitySetUriInfo;
import org.apache.olingo.odata2.api.uri.info.GetEntityUriInfo;
import org.apache.olingo.odata2.processor.api.jpa.factory.JPAAccessFactory;
import org.apache.olingo.odata2.processor.api.jpa.factory.ODataJPAAccessFactory;
import org.apache.olingo.odata2.processor.api.jpa.jpql.JPQLContext;
import org.apache.olingo.odata2.processor.api.jpa.jpql.JPQLContext.JPQLContextBuilder;
import org.apache.olingo.odata2.processor.api.jpa.jpql.JPQLContextType;
import org.apache.olingo.odata2.processor.api.jpa.jpql.JPQLStatement.JPQLStatementBuilder;
import org.apache.olingo.odata2.processor.core.jpa.ODataJPAContextImpl;
import org.apache.olingo.odata2.processor.core.jpa.access.data.JPAProcessorImplTest;
import org.apache.olingo.odata2.processor.core.jpa.common.ODataJPATestConstants;
import org.apache.olingo.odata2.processor.core.jpa.factory.ODataJPAFactoryImpl;
import org.apache.olingo.odata2.processor.core.jpa.jpql.JPQLSelectContext.JPQLSelectContextBuilder;
import org.apache.olingo.odata2.processor.core.jpa.jpql.JPQLSelectSingleContext.JPQLSelectSingleContextBuilder;
import org.easymock.EasyMock;
import org.junit.Test;

public class JPQLBuilderFactoryTest {

  @Test
  public void testGetStatementBuilderFactoryforSelect() throws ODataException {

    GetEntitySetUriInfo getEntitySetView = getUriInfo();

    // Build JPQL Context
    JPQLContext selectContext = JPQLContext.createBuilder(JPQLContextType.SELECT, getEntitySetView).build();
    JPQLStatementBuilder statementBuilder =
        new ODataJPAFactoryImpl().getJPQLBuilderFactory().getStatementBuilder(selectContext);

    assertTrue(statementBuilder instanceof JPQLSelectStatementBuilder);

  }

  @Test
  public void testGetStatementBuilderFactoryforSelectSingle() throws ODataException {

    GetEntityUriInfo getEntityView = getEntityUriInfo();

    // Build JPQL Context
    JPQLContext selectContext = JPQLContext.createBuilder(JPQLContextType.SELECT_SINGLE, getEntityView).build();
    JPQLStatementBuilder statementBuilder =
        new ODataJPAFactoryImpl().getJPQLBuilderFactory().getStatementBuilder(selectContext);

    assertTrue(statementBuilder instanceof JPQLSelectSingleStatementBuilder);

  }

  @Test
  public void testGetStatementBuilderFactoryforJoinSelect() throws ODataException {

    GetEntitySetUriInfo getEntitySetView = getUriInfo();

    // Build JPQL Context
    JPQLContext selectContext = JPQLContext.createBuilder(JPQLContextType.JOIN, getEntitySetView).build();
    JPQLStatementBuilder statementBuilder =
        new ODataJPAFactoryImpl().getJPQLBuilderFactory().getStatementBuilder(selectContext);

    assertTrue(statementBuilder instanceof JPQLJoinStatementBuilder);

  }

  @Test
  public void testGetStatementBuilderFactoryforJoinSelectSingle() throws ODataException {

    GetEntityUriInfo getEntityView = getEntityUriInfo();

    // Build JPQL Context
    JPQLContext selectContext = JPQLContext.createBuilder(JPQLContextType.JOIN_SINGLE, getEntityView).build();
    JPQLStatementBuilder statementBuilder =
        new ODataJPAFactoryImpl().getJPQLBuilderFactory().getStatementBuilder(selectContext);

    assertTrue(statementBuilder instanceof JPQLJoinSelectSingleStatementBuilder);

  }

  @Test
  public void testGetContextBuilderforDelete() throws ODataException {

    // Build JPQL ContextBuilder
    JPQLContextBuilder contextBuilder =
        new ODataJPAFactoryImpl().getJPQLBuilderFactory().getContextBuilder(JPQLContextType.DELETE);

    assertNull(contextBuilder);

  }

  @Test
  public void testGetContextBuilderforSelect() throws ODataException {

    // Build JPQL ContextBuilder
    JPQLContextBuilder contextBuilder =
        new ODataJPAFactoryImpl().getJPQLBuilderFactory().getContextBuilder(JPQLContextType.SELECT);

    assertNotNull(contextBuilder);
    assertTrue(contextBuilder instanceof JPQLSelectContextBuilder);

  }

  @Test
  public void testGetContextBuilderforSelectSingle() throws ODataException {

    // Build JPQL ContextBuilder
    JPQLContextBuilder contextBuilder =
        new ODataJPAFactoryImpl().getJPQLBuilderFactory().getContextBuilder(JPQLContextType.SELECT_SINGLE);

    assertNotNull(contextBuilder);
    assertTrue(contextBuilder instanceof JPQLSelectSingleContextBuilder);

  }

  private GetEntitySetUriInfo getUriInfo() throws EdmException {
    GetEntitySetUriInfo getEntitySetView = EasyMock.createMock(GetEntitySetUriInfo.class);
    EdmEntitySet edmEntitySet = EasyMock.createMock(EdmEntitySet.class);
    EdmEntityType edmEntityType = EasyMock.createMock(EdmEntityType.class);
    EasyMock.expect(edmEntityType.getMapping()).andStubReturn(null);
    EasyMock.expect(edmEntityType.getName()).andStubReturn("SOItem");
    EasyMock.replay(edmEntityType);
    OrderByExpression orderByExpression = EasyMock.createMock(OrderByExpression.class);
    EasyMock.expect(getEntitySetView.getTargetEntitySet()).andStubReturn(edmEntitySet);
    EdmEntitySet startEdmEntitySet = EasyMock.createMock(EdmEntitySet.class);
    EdmEntityType startEdmEntityType = EasyMock.createMock(EdmEntityType.class);
    EasyMock.expect(startEdmEntityType.getMapping()).andStubReturn(null);
    EasyMock.expect(startEdmEntityType.getName()).andStubReturn("SOHeader");
    EasyMock.expect(startEdmEntitySet.getEntityType()).andStubReturn(startEdmEntityType);
    EasyMock.expect(getEntitySetView.getStartEntitySet()).andStubReturn(startEdmEntitySet);
    EasyMock.replay(startEdmEntityType, startEdmEntitySet);
    EasyMock.expect(getEntitySetView.getOrderBy()).andStubReturn(orderByExpression);
    EasyMock.expect(getEntitySetView.getSelect()).andStubReturn(null);
    EasyMock.expect(getEntitySetView.getFilter()).andStubReturn(null);
    List<NavigationSegment> navigationSegments = new ArrayList<NavigationSegment>();
    EasyMock.expect(getEntitySetView.getNavigationSegments()).andStubReturn(navigationSegments);
    KeyPredicate keyPredicate = EasyMock.createMock(KeyPredicate.class);
    EdmProperty kpProperty = EasyMock.createMock(EdmProperty.class);
    EdmSimpleType edmType = EasyMock.createMock(EdmSimpleType.class);
    EdmMapping edmMapping = EasyMock.createMock(EdmMapping.class);
    EasyMock.expect(edmMapping.getInternalName()).andStubReturn("Field1");
    EasyMock.expect(keyPredicate.getLiteral()).andStubReturn("1");
    try {
      EasyMock.expect(kpProperty.getName()).andStubReturn("Field1");
      EasyMock.expect(kpProperty.getType()).andStubReturn(edmType);

      EasyMock.expect(kpProperty.getMapping()).andStubReturn(edmMapping);

    } catch (EdmException e2) {
      fail("this should not happen");
    }
    EasyMock.expect(keyPredicate.getProperty()).andStubReturn(kpProperty);
    EasyMock.replay(edmMapping, edmType, kpProperty, keyPredicate);
    List<KeyPredicate> keyPredicates = new ArrayList<KeyPredicate>();
    keyPredicates.add(keyPredicate);
    EasyMock.expect(getEntitySetView.getKeyPredicates()).andStubReturn(keyPredicates);
    EasyMock.replay(getEntitySetView);
    EasyMock.expect(edmEntitySet.getEntityType()).andStubReturn(edmEntityType);
    EasyMock.replay(edmEntitySet);
    return getEntitySetView;
  }

  private GetEntityUriInfo getEntityUriInfo() throws EdmException {
    GetEntityUriInfo getEntityView = EasyMock.createMock(GetEntityUriInfo.class);
    EdmEntitySet edmEntitySet = EasyMock.createMock(EdmEntitySet.class);
    EdmEntityType edmEntityType = EasyMock.createMock(EdmEntityType.class);
    EasyMock.expect(edmEntityType.getKeyProperties()).andStubReturn(new ArrayList<EdmProperty>());
    EasyMock.expect(edmEntityType.getMapping()).andStubReturn(null);
    EasyMock.expect(edmEntityType.getName()).andStubReturn("");
    EasyMock.expect(edmEntitySet.getEntityType()).andStubReturn(edmEntityType);
    EasyMock.expect(getEntityView.getSelect()).andStubReturn(null);
    EasyMock.expect(getEntityView.getTargetEntitySet()).andStubReturn(edmEntitySet);
    EdmEntitySet startEdmEntitySet = EasyMock.createMock(EdmEntitySet.class);
    EdmEntityType startEdmEntityType = EasyMock.createMock(EdmEntityType.class);
    EasyMock.expect(startEdmEntityType.getMapping()).andStubReturn(null);
    EasyMock.expect(startEdmEntityType.getName()).andStubReturn("SOHeader");
    EasyMock.expect(startEdmEntitySet.getEntityType()).andStubReturn(startEdmEntityType);
    EasyMock.expect(getEntityView.getStartEntitySet()).andStubReturn(startEdmEntitySet);
    EasyMock.replay(startEdmEntityType, startEdmEntitySet);
    EasyMock.replay(edmEntityType, edmEntitySet);
    EasyMock.expect(getEntityView.getKeyPredicates()).andStubReturn(new ArrayList<KeyPredicate>());
    List<NavigationSegment> navigationSegments = new ArrayList<NavigationSegment>();
    EasyMock.expect(getEntityView.getNavigationSegments()).andStubReturn(navigationSegments);
    EasyMock.replay(getEntityView);
    return getEntityView;
  }

  @Test
  public void testJPAAccessFactory() {
    ODataJPAFactoryImpl oDataJPAFactoryImpl = new ODataJPAFactoryImpl();
    JPAAccessFactory jpaAccessFactory = oDataJPAFactoryImpl.getJPAAccessFactory();
    ODataJPAContextImpl oDataJPAContextImpl = new ODataJPAContextImpl();
    Class<?> clazz = oDataJPAContextImpl.getClass();
    try {
      Field field = clazz.getDeclaredField("em");
      field.setAccessible(true);
      field.set(oDataJPAContextImpl, new JPAProcessorImplTest().getLocalEntityManager());
    } catch (SecurityException e) {
      fail(ODataJPATestConstants.EXCEPTION_MSG_PART_1 + e.getMessage() + ODataJPATestConstants.EXCEPTION_MSG_PART_2);
    } catch (NoSuchFieldException e) {
      fail(ODataJPATestConstants.EXCEPTION_MSG_PART_1 + e.getMessage() + ODataJPATestConstants.EXCEPTION_MSG_PART_2);
    } catch (IllegalArgumentException e) {
      fail(ODataJPATestConstants.EXCEPTION_MSG_PART_1 + e.getMessage() + ODataJPATestConstants.EXCEPTION_MSG_PART_2);
    } catch (IllegalAccessException e) {
      fail(ODataJPATestConstants.EXCEPTION_MSG_PART_1 + e.getMessage() + ODataJPATestConstants.EXCEPTION_MSG_PART_2);
    }
    EntityManagerFactory emf = new EntityManagerFactory() {

      @Override
      public boolean isOpen() {
        return false;
      }

      @Override
      public Map<String, Object> getProperties() {
        return null;
      }

      @Override
      public PersistenceUnitUtil getPersistenceUnitUtil() {
        return null;
      }

      @Override
      public Metamodel getMetamodel() {
        return null;
      }

      @Override
      public CriteriaBuilder getCriteriaBuilder() {
        return null;
      }

      @Override
      public Cache getCache() {
        return null;
      }

      @SuppressWarnings("rawtypes")
      @Override
      public EntityManager createEntityManager(final Map arg0) {
        return null;
      }

      @Override
      public EntityManager createEntityManager() {
        return null;
      }

      @Override
      public void close() {}
    };
    oDataJPAContextImpl.setEntityManagerFactory(emf);
    oDataJPAContextImpl.setPersistenceUnitName("pUnit");

    assertNotNull(jpaAccessFactory.getJPAProcessor(oDataJPAContextImpl));
    assertNotNull(jpaAccessFactory.getJPAEdmModelView(oDataJPAContextImpl));

  }

  @Test
  public void testOdataJpaAccessFactory() {

    ODataJPAFactoryImpl oDataJPAFactoryImpl = new ODataJPAFactoryImpl();
    ODataJPAAccessFactory jpaAccessFactory = oDataJPAFactoryImpl.getODataJPAAccessFactory();
    ODataJPAContextImpl oDataJPAContextImpl = new ODataJPAContextImpl();

    EntityManagerFactory emf = new EntityManagerFactory() {

      @Override
      public boolean isOpen() {
        // TODO Auto-generated method stub
        return false;
      }

      @Override
      public Map<String, Object> getProperties() {
        // TODO Auto-generated method stub
        return null;
      }

      @Override
      public PersistenceUnitUtil getPersistenceUnitUtil() {
        // TODO Auto-generated method stub
        return null;
      }

      @Override
      public Metamodel getMetamodel() {
        // TODO Auto-generated method stub
        return null;
      }

      @Override
      public CriteriaBuilder getCriteriaBuilder() {
        // TODO Auto-generated method stub
        return null;
      }

      @Override
      public Cache getCache() {
        // TODO Auto-generated method stub
        return null;
      }

      @SuppressWarnings("rawtypes")
      @Override
      public EntityManager createEntityManager(final Map arg0) {
        // TODO Auto-generated method stub
        return null;
      }

      @Override
      public EntityManager createEntityManager() {
        // TODO Auto-generated method stub
        return null;
      }

      @Override
      public void close() {
        // TODO Auto-generated method stub

      }
    };
    oDataJPAContextImpl.setEntityManagerFactory(emf);
    oDataJPAContextImpl.setPersistenceUnitName("pUnit");

    assertNotNull(jpaAccessFactory.getODataJPAMessageService(new Locale("en")));
    assertNotNull(jpaAccessFactory.createODataJPAContext());
    assertNotNull(jpaAccessFactory.createJPAEdmProvider(oDataJPAContextImpl));
    assertNotNull(jpaAccessFactory.createODataProcessor(oDataJPAContextImpl));

  }
}
