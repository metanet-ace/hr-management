package com.metanet.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QEmpHistoryVO is a Querydsl query type for EmpHistoryVO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEmpHistoryVO extends EntityPathBase<EmpHistoryVO> {

    private static final long serialVersionUID = 1737901836L;

    public static final QEmpHistoryVO empHistoryVO = new QEmpHistoryVO("empHistoryVO");

    public final NumberPath<Integer> beforeDept = createNumber("beforeDept", Integer.class);

    public final NumberPath<Integer> beforePos = createNumber("beforePos", Integer.class);

    public final NumberPath<Integer> deptNo = createNumber("deptNo", Integer.class);

    public final NumberPath<Integer> empHisno = createNumber("empHisno", Integer.class);

    public final NumberPath<Integer> empNo = createNumber("empNo", Integer.class);

    public final StringPath issuedContent = createString("issuedContent");

    public final DatePath<java.util.Date> issuedDate = createDate("issuedDate", java.util.Date.class);

    public final StringPath issuedOrder = createString("issuedOrder");

    public final NumberPath<Integer> posNo = createNumber("posNo", Integer.class);

    public QEmpHistoryVO(String variable) {
        super(EmpHistoryVO.class, forVariable(variable));
    }

    public QEmpHistoryVO(Path<? extends EmpHistoryVO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmpHistoryVO(PathMetadata metadata) {
        super(EmpHistoryVO.class, metadata);
    }

}

