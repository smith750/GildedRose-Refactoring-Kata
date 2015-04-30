package com.gildedrose;

public class NormalItemUpdateHelper extends ItemUpdateHelper {
    public NormalItemUpdateHelper(Item item) {
        super(item);
    }

    @Override
    public void updateItem() {
        decreaseQuality();
        if (isPastSellIn()) {
            // past sell in, double up the decreasing
            decreaseQuality();
        }
        decreaseSellIn();
    }
}
