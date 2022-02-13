package com.metanet.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPositionVO is a Querydsl query type for PositionVO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPositionVO extends EntityPathBase<PositionVO> {

    private static final long serialVersionUID = -1849217015L;

    public static final QPositionVO positionVO = new QPositionVO("positionVO");

    public final NumberPath<Integer> posMaxsal = createNumber("posMaxsal", Integer.class);

    public final NumberPath<Integer> posMinsal = createNumber("posMinsal", Integer.class);

    public final StringPath posName = createString("posName");

    public final NumberPath<Integer> posNo = createNumber("posNo", Integer.class);

    public QPositionVO(String variable) {
        super(PositionVO.class, forVariable(variable));
    }

    public QPositionVO(Path<? extends PositionVO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPositionVO(PathMetadata metadata) {
        super(PositionVO.class, metadata);
    }

}

