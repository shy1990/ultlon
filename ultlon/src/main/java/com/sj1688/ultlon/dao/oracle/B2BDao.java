package com.sj1688.ultlon.dao.oracle;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public interface B2BDao {
    public List<Map<String,Serializable>> findSomthing();
}