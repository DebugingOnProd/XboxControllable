package org.lhq.common;


public interface Identifiable extends Nameable {
     Identifier getIdentifier();

    @Override
    default  String getName()
    {
        return this.getIdentifier().getName();
    }
}
