package com.metanet.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEmployeeVO is a Querydsl query type for EmployeeVO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEmployeeVO extends EntityPathBase<EmployeeVO> {

    private static final long serialVersionUID = 1232744526L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEmployeeVO employeeVO = new QEmployeeVO("employeeVO");

    public final QDepartmentVO dept;

    public final StringPath empCareer = createString("empCareer");

    public final StringPath empDegree = createString("empDegree");

    public final StringPath empEmail = createString("empEmail");

    public final StringPath empGender = createString("empGender");

    public final DatePath<java.util.Date> empHiredate = createDate("empHiredate", java.util.Date.class);

    public final StringPath empMil = createString("empMil");

    public final StringPath empName = createString("empName");

    public final NumberPath<Integer> empNo = createNumber("empNo", Integer.class);

    public final StringPath empPhone = createString("empPhone");

    public final StringPath empPhoto = createString("empPhoto");

    public final StringPath empPwd = createString("empPwd");

    public final DatePath<java.util.Date> empRetdate = createDate("empRetdate", java.util.Date.class);

    public final NumberPath<Integer> empSal = createNumber("empSal", Integer.class);

    public final StringPath empSsc = createString("empSsc");

    public final QPositionVO pos;

    public QEmployeeVO(String variable) {
        this(EmployeeVO.class, forVariable(variable), INITS);
    }

    public QEmployeeVO(Path<? extends EmployeeVO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEmployeeVO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEmployeeVO(PathMetadata metadata, PathInits inits) {
        this(EmployeeVO.class, metadata, inits);
    }

    public QEmployeeVO(Class<? extends EmployeeVO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.dept = inits.isInitialized("dept") ? new QDepartmentVO(forProperty("dept")) : null;
        this.pos = inits.isInitialized("pos") ? new QPositionVO(forProperty("pos")) : null;
    }

}

