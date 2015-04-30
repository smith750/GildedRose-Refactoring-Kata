package com.gildedrose;

public class IncreaseQualityItemUpdateHelper extends ItemUpdateHelper {
    public IncreaseQualityItemUpdateHelper(Item item) {
        super(item);
    }

    @Override
    public void updateItem() {
        increaseQuality();
        decreaseSellIn();
    }
}
