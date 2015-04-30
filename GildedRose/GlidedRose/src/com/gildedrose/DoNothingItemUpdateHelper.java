package com.gildedrose;

public class DoNothingItemUpdateHelper extends ItemUpdateHelper {
    public DoNothingItemUpdateHelper(Item item) {
        super(item);
    }

    @Override
    public void updateItem() {
        // do nothing
    }
}
