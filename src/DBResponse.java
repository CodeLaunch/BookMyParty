/*
 * Copyright 2012 Alcatel-Lucent
 * All Rights Reserved
 * This is unpublished proprietary source code of Alcatel-Lucent.
 * The copyright notice above does not evidence any actual
 * or intended publication of such source code.
 *
 * Not to be disclosed or used except in accordance with applicable
 * agreements.
 */


	import java.util.*;

	/**
	 * This class represents a typical response that can be sent to a client,
	 * for any request.It contains a data that is a vector in which
	 * anything can be sent.
	 *
	 */
	public class DBResponse  
	{	    
	    public enum StatusCode {
	        UNKNOWN,
	        DUPLICATE,
	        INTEGRITY_CONSTRAINT_VIOLATION
	    };
	    
	    /**
	     * The number of records are selected, updated, inserted or deleted
	     *
	     */
	    private int _records = 0;

	    /**
	     * Flag indicates the status of the operation
	     *
	     */
	    private boolean _status = false;

	    /**
	     * The error description message.
	     *
	     */
	    private String  _description = null;

	    /**
	     * Store the data
	     *
	     */
	    private Vector<NameValues>  _data = null;
	    
	    private StatusCode _status_code = StatusCode.UNKNOWN;
	    
	    public int getRecordNumber() {
	        return _records;
	    }

	    public void setRecordNumber(int record_num) {
	        _records = record_num;
	    }

	    /**
	     * Construct the object, default the response is successful.
	     *
	     */
	    public DBResponse() {
	        _status = false;
	    }

	    public DBResponse(boolean bool) {
	        _status = bool;
	    }

	    public void setStatus(boolean s) {
	        _status = s;
	    }

	    public boolean getStatus() {
	        return _status;
	    }

	    public void setDescription(String d) {
	        _description = d;
	    }

	    public String getDescription() {
	        return _description;
	    }

	    public void setData(Vector<NameValues> v) {
	        if (_data != null) {
	            _data.clear();
	        }
	        _data = v;
	    }
	    
	    public void addData(Vector<NameValues> v) {
	        if (_data != null) {
	        	_data.addAll(v);
	        }
	        else
	        {
	        	setData(v);
	        }
	    }
	    public int addRecordNumber(int record_num) {
	        return (_records+record_num);
	    }

	    public Vector<NameValues> getData() {
	        return _data;
	    }
	    
	    public void setStatusCode(StatusCode code)
	    {
	        _status_code = code;
	    }
	    
	    public StatusCode getStatusCode()
	    {
	        return _status_code;
	    }
}
