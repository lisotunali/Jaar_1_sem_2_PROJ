package GUI;

import BACKEND.Marketplace;

public class singletonMarketplace {
    private static Marketplace marketplace = new Marketplace();

    private singletonMarketplace() {
    }

    public static Marketplace getInstance() {
        return marketplace;
    }
}
