package com.like.system.core.jpa.domain;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;

public class AuditorDetailsType implements UserType {
	
	@Override
    public int[] sqlTypes() {
        return new int[]{Types.VARCHAR, Types.VARCHAR};
    }

    @Override
    public Class<AuditorDetails> returnedClass() {
        return AuditorDetails.class;
    }

    @Override
    public boolean equals(Object o, Object o1) throws HibernateException {
        if(o == o1)
            return true;
        if (Objects.isNull(o) || Objects.isNull(o1))
            return false;

        return o.equals(o1);
    }

    @Override
    public int hashCode(Object o) throws HibernateException {
        return o.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException, SQLException {
        String loggedUser = resultSet.getString(strings[0]);

        if(resultSet.wasNull())
            return null;       

        String hostIp = resultSet.getString(strings[1]);

        AuditorDetails currentAuditor = new AuditorDetails(loggedUser, hostIp);
        return currentAuditor;
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException, SQLException {
        if (Objects.isNull(o)){
            preparedStatement.setNull(i,Types.VARCHAR);
        }
        else {
            AuditorDetails currentAuditor = (AuditorDetails) o;
            preparedStatement.setString(i,currentAuditor.getLoggedUser());            
            preparedStatement.setString(i+1,currentAuditor.getHostIp());
        }
    }

    @Override
    public Object deepCopy(Object o) throws HibernateException {
        if (Objects.isNull(o))
            return null;

        AuditorDetails currentAuditor = (AuditorDetails) o;

        return new AuditorDetails(currentAuditor.getLoggedUser(), currentAuditor.getHostIp());
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object o) throws HibernateException {
        return (Serializable) o;
    }

    @Override
    public Object assemble(Serializable serializable, Object o) throws HibernateException {
        return serializable;
    }

    @Override
    public Object replace(Object o, Object o1, Object o2) throws HibernateException {
        return o;
    }
}
