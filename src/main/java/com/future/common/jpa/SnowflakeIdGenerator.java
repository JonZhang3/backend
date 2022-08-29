package com.future.common.jpa;

import com.future.common.utils.IdGenerator;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class SnowflakeIdGenerator implements IdentifierGenerator {

    public static final String GENERATOR_NAME = "SNOWFLAKE_ID";

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return IdGenerator.SNOWFLAKE_ID.next();
    }
}
