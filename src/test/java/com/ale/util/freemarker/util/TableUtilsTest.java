package com.ale.util.freemarker.util; 

import com.ale.common.util.mine.TableUtils;
import com.ale.common.util.freemarker.TableMetaData;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.List;

/** 
 * TableUtils Tester. 
 * 
 * @author <Authors name> 
 * @since <pre></pre> 
 * @version 1.0 
 */ 
public class TableUtilsTest { 

    @Before
    public void before() throws Exception { 
    } 
    
    @After
    public void after() throws Exception { 
    } 
        
    /** 
     * Method: getConnection() 
     */ 
    @Test
    public void testGetConnection() throws Exception {

    } 
    
        
    /** 
     * Method: getResultSet() 
     */ 
    @Test
    public void testGetResultSet() throws Exception { 
        //TODO: Test goes here... 
    } 
    
        
    /** 
     * Method: getTableMetaData() 
     */ 
    @Test
    public void testGetTableMetaData() throws Exception {
        List<TableMetaData> tableMetaDatas = TableUtils.getTableMetaData();
        for (TableMetaData tableMetaData : tableMetaDatas) {
            System.out.println(tableMetaData);
        }
    } 
    
            
    /** 
     * Method: processName(String str, String target) 
     */ 
    @Test
    public void testProcessName() throws Exception { 
        //TODO: Test goes here... 
                /* 
                try { 
                   Method method = TableUtils.getClass().getMethod("processName", String.class, String.class); 
                   method.setAccessible(true); 
                   method.invoke(<Object>, <Parameters>); 
                } catch(NoSuchMethodException e) { 
                } catch(IllegalAccessException e) { 
                } catch(InvocationTargetException e) { 
                } 
                */ 
            } 

    
    /** 
     * Method: getParameter(String key) 
     */ 
    @Test
    public void testGetParameter() throws Exception { 
        //TODO: Test goes here... 
                /* 
                try { 
                   Method method = TableUtils.getClass().getMethod("getParameter", String.class); 
                   method.setAccessible(true); 
                   method.invoke(<Object>, <Parameters>); 
                } catch(NoSuchMethodException e) { 
                } catch(IllegalAccessException e) { 
                } catch(InvocationTargetException e) { 
                } 
                */ 
            } 


} 
