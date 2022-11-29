package org.lhq.common;


public class Identifier implements Nameable {
    protected final String domain;
    protected final String name;

    @Override
    public String getName() {
        return name;
    }

    public Identifier(String resource) {
        String[] res = getInTwoParts(resource);
        this.domain = res[0];
        this.name = res[1];
    }

    private static String[] getInTwoParts(String o) {
        int index = o.indexOf(':');
        if (index <= 0)
            throw new IllegalArgumentException("The given string isn't a valid resource name!");
        return new String[]{o.substring(0, index), o.substring(index + 1)};
    }
}
