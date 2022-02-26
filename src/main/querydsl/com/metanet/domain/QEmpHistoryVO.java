package com.metanet.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEmpHistoryVO is a Querydsl query type for EmpHistoryVO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEmpHistoryVO extends EntityPathBase<EmpHistoryVO> {

    private static final long serialVersionUID = 1737901836L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEmpHistoryVO empHistoryVO = new QEmpHistoryVO("empHistoryVO");

    public final StringPath beforeDept = createString("beforeDept");

    public final StringPath beforePos = createString("beforePos");

    public final NumberPath<Integer> deptNo = createNumber("deptNo", Integer.class);

    public final QEmployeeVO emp;

    public final NumberPath<Integer> empHisno = createNumber("empHisno", Integer.class);

    public final StringPath issuedContent = createString("issuedContent");

    public final DatePath<java.util.Date> issuedDate = createDate("issuedDate", java.util.Date.class);

    public final StringPath issuedOrder = createString("issuedOrder");

    public final StringPath nowDeptName = createString("nowDeptName");

    public final StringPath nowPosName = createString("nowPosName");

    public final NumberPath<Integer> posNo = createNumber("posNo", Integer.class);

    public QEmpHistoryVO(String variable) {
        this(EmpHistoryVO.class, forVariable(variable), INITS);
    }

    public QEmpHistoryVO(Path<? extends EmpHistoryVO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEmpHistoryVO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEmpHistoryVO(PathMetadata metadata, PathInits inits) {
        this(EmpHistoryVO.class, metadata, inits);
    }

    public QEmpHistoryVO(Class<? extends EmpHistoryVO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.emp = inits.isInitialized("emp") ? new QEmployeeVO(forProperty("emp"), inits.get("emp")) : null;
    }

}

