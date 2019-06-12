package org.csu.mypetstore_spring.persistence;

import org.csu.mypetstore_spring.domain.Sequence;

public interface SequenceMapper {
    Sequence getSequence(Sequence sequence);

    void updateSequence(Sequence sequence);
}
