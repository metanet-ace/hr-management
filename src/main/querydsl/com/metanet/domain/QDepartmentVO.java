package com.metanet.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDepartmentVO is a Querydsl query type for DepartmentVO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDepartmentVO extends EntityPathBase<DepartmentVO> {

    private static final long serialVersionUID = 46003762L;

    public static final QDepartmentVO departmentVO = new QDepartmentVO("departmentVO");

    public final NumberPath<Integer> deptHead = createNumber("deptHead", Integer.class);

    public final StringPath deptName = createString("deptName");

    public final NumberPath<Integer> deptNo = createNumber("deptNo", Integer.class);

    public QDepartmentVO(String variable) {
        super(DepartmentVO.class, forVariable(variable));
    }

    public QDepartmentVO(Path<? extends DepartmentVO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDepartmentVO(PathMetadata metadata) {
        super(DepartmentVO.class, metadata);
    }

}

