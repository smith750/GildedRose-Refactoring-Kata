package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {
    @Test
    public void testRegularItem() {
        assertItem(new Item("+5 Dexterity Vest", 10, 20), 9, 19);
    }

    @Test
    public void testQualityGainingItem() {
        assertItem(new Item("Aged Brie", 2, 0), 1, 1);
    }

    @Test
    public void testQualityGainingItemNeverGreaterThanMax() {
        assertItem(new Item("Aged Brie", 2, 50), 1, 50);
    }

    @Test
    public void testAnotherRegularItem() {
        assertItem(new Item("Elixir of the Mongoose", 5, 7), 4, 6);
    }

    @Test
    public void testAnotherRegularItemPastSellIn() {
        assertItem(new Item("Elixir of the Mongoose", -1, 7), -2, 5);
    }

    @Test
    public void testAnotherRegularItemNoNegativeQuality() {
        assertItem(new Item("Elixir of the Mongoose", -1, 1), -2, 0);
    }

    @Test
    public void testUnchangingItem_zeroSellIn() {
        assertItem(new Item("Sulfuras, Hand of Ragnaros", 0, 80), 0, 80);
    }

    @Test
    public void testUnchangingItem_negativeSellIn() {
        assertItem(new Item("Sulfuras, Hand of Ragnaros", -1, 80), -1, 80);
    }

    @Test
    public void testTimeSensitiveMoreThanTenDaysOut() {
        assertItem(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20), 14, 21);
    }

    @Test
    public void testTimeSensitiveLessThanTenDaysOut() {
        assertItem(new Item("Backstage passes to a TAFKAL80ETC concert", 10, 45), 9, 47);
    }

    @Test
    public void testTimeSensitiveLessThanFiveDaysOut() {
        assertItem(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 45), 4, 48);
    }

    @Test
    public void testTimeSensitiveLessThanFiveDaysOutMaxQuality() {
        assertItem(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49), 4, 50);
    }

    @Test
    public void testTimeSensitivePastSellIn() {
        assertItem(new Item("Backstage passes to a TAFKAL80ETC concert", -1, 0), -2, 0);
    }

    @Test
    public void testConjuredItem() {
        assertItem(new Item("Conjured Mana Cake", 3, 6), 2, 4);
    }

    protected void assertItem(Item item, int expectedSellIn, int expectedQuality) {
        Item[] items = new Item[] { item };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Item " + item.name + " has an incorrect sell in", expectedSellIn, item.sellIn);
        assertEquals("Item "+item.name+" has an incorrect quality", expectedQuality, item.quality);
    }

}
