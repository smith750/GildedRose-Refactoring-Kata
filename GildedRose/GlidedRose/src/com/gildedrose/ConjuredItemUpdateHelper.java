package com.gildedrose;

public class ConjuredItemUpdateHelper extends ItemUpdateHelper {
    public ConjuredItemUpdateHelper(Item item) {
        super(item);
    }

    @Override
    public void updateItem() {
        decreaseQuality(); // double the decreasing of quality
        decreaseQuality();
        decreaseSellIn();
    }
}
