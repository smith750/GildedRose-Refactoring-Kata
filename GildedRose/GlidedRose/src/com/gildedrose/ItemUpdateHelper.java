package com.gildedrose;

public abstract class ItemUpdateHelper {
    public static final int MAXIMUM_QUALITY = 50;
    public static final int MINIMUM_QUALITY = 0;
    public static final int SELL_IN_DEADLINE_DAYS = 0;
    protected Item item;

    public ItemUpdateHelper(Item item) {
        this.item = item;
    }

    public abstract void updateItem();

    protected void minimizeQuality() {
        item.quality = MINIMUM_QUALITY;
    }

    protected boolean isPastSellIn() {
        return item.sellIn < SELL_IN_DEADLINE_DAYS;
    }

    protected void decreaseQuality() {
        if (item.quality > MINIMUM_QUALITY) {
            item.quality -= 1;
        }
    }

    protected void increaseQuality() {
        if (item.quality < MAXIMUM_QUALITY) {
            item.quality += 1;
        }
    }

    protected void decreaseSellIn() {
        item.sellIn -= 1;
    }
}