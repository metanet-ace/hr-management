package com.metanet.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEmpWorkingtimeVO is a Querydsl query type for EmpWorkingtimeVO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEmpWorkingtimeVO extends EntityPathBase<EmpWorkingtimeVO> {

    private static final long serialVersionUID = -173746698L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEmpWorkingtimeVO empWorkingtimeVO = new QEmpWorkingtimeVO("empWorkingtimeVO");

    public final QEmployeeVO emp;

    public final DatePath<java.util.Date> workEnd = createDate("workEnd", java.util.Date.class);

    public final NumberPath<Integer> workNo = createNumber("workNo", Integer.class);

    public final DatePath<java.util.Date> workStart = createDate("workStart", java.util.Date.class);

    public final StringPath workType = createString("workType");

    public QEmpWorkingtimeVO(String variable) {
        this(EmpWorkingtimeVO.class, forVariable(variable), INITS);
    }

    public QEmpWorkingtimeVO(Path<? extends EmpWorkingtimeVO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEmpWorkingtimeVO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEmpWorkingtimeVO(PathMetadata metadata, PathInits inits) {
        this(EmpWorkingtimeVO.class, metadata, inits);
    }

    public QEmpWorkingtimeVO(Class<? extends EmpWorkingtimeVO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.emp = inits.isInitialized("emp") ? new QEmployeeVO(forProperty("emp"), inits.get("emp")) : null;
    }

}

