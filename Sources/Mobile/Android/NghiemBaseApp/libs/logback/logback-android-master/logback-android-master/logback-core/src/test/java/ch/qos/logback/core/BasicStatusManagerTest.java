/**
 * Logback: the reliable, generic, fast and flexible logging framework.
 * Copyright (C) 1999-2013, QOS.ch. All rights reserved.
 *
 * This program and the accompanying materials are dual-licensed under
 * either the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation
 *
 *   or (per the licensee's choosing)
 *
 * under the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation.
 */
package ch.qos.logback.core;

import static ch.qos.logback.core.BasicStatusManager.MAX_HEADER_COUNT;
import static ch.qos.logback.core.BasicStatusManager.TAIL_SIZE;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ch.qos.logback.core.spi.ContextAware;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.status.ErrorStatus;
import ch.qos.logback.core.status.OnConsoleStatusListener;
import ch.qos.logback.core.status.Status;
import ch.qos.logback.core.status.StatusListener;


public class BasicStatusManagerTest {


  BasicStatusManager bsm = new BasicStatusManager();
  Context context = new ContextBase();
  ContextAware contextAware = new ContextAwareBase();
  OnConsoleStatusListener csl = new OnConsoleStatusListener();

  @Before
  public void before() {
    contextAware.setContext(context);
    csl.setContext(context);
    bsm.clear();
  }

  @Test
  public void smoke() {
    bsm.add(new ErrorStatus("hello", this));
    assertEquals(Status.ERROR, bsm.getLevel());

    List<Status> statusList = bsm.getCopyOfStatusList();
    assertNotNull(statusList);
    assertEquals(1, statusList.size());
    assertEquals("hello", statusList.get(0).getMessage());
  }

  @Test
  public void many() {
    int margin = 300;
    int len = MAX_HEADER_COUNT+TAIL_SIZE+margin;
    for(int i = 0; i < len; i++) {
      bsm.add(new ErrorStatus(""+i, this));
    }

    List<Status> statusList = bsm.getCopyOfStatusList();
    assertNotNull(statusList);
    assertEquals(MAX_HEADER_COUNT+TAIL_SIZE, statusList.size());
    List<Status> witness = new ArrayList<Status>();
    for(int i = 0; i < MAX_HEADER_COUNT; i++) {
      witness.add(new ErrorStatus(""+i, this));
    }
    for(int i = 0; i < TAIL_SIZE; i++) {
      witness.add(new ErrorStatus(""+(MAX_HEADER_COUNT+margin+i), this));
    }
    assertEquals(witness, statusList);
  }

  @Test
  public void returnsTrueForNewlyAddedConsoleListener() {
    assertTrue(bsm.addUniquely(csl, contextAware));
    assertEquals(OnConsoleStatusListener.class, bsm.getCopyOfStatusListenerList().get(0).getClass());

    List<StatusListener> statusList = bsm.getCopyOfStatusListenerList();
    assertEquals(1, statusList.size());
  }

  @Test
  public void returnsFalseWhenNoConsoleListenerAdded() {
    bsm.addUniquely(csl, contextAware);
    assertFalse(bsm.addUniquely(csl, contextAware));

    List<StatusListener> statusList = bsm.getCopyOfStatusListenerList();
    assertEquals(1, statusList.size());
  }

  @Test
  public void addsConsoleStatusListenerOnlyIfAbsent() {
    bsm.addUniquely(csl, contextAware);

    List<StatusListener> statusList = bsm.getCopyOfStatusListenerList();
    assertEquals(1, statusList.size());
    assertEquals(csl, statusList.get(0));

    bsm.addUniquely(csl, contextAware);

    statusList = bsm.getCopyOfStatusListenerList();
    assertEquals(1, statusList.size());
    assertEquals(csl, statusList.get(0));
  }
}
