var assert = require("assert")
var gilded_rose = require("../src/gilded_rose.js")

function assertItem(item, expected_sell_in, expected_quality) {
	gilded_rose.items.push(item)
	gilded_rose.update_quality()
	assert.equal(expected_sell_in, item.sell_in)
	assert.equal(expected_quality, item.quality)
	gilded_rose.items.pop()
}

describe("update_quality()",function() {
	it('should decrease sell_in by 1 and quality by 1 for normal items',function() {
		var item = new gilded_rose.Item('+5 Dexterity Vest', 10, 20)
		assertItem(item, 9, 19)
	})
	it('should decrease sell_in by 1 and increase quality by 1 of aged brie',function() {
		var item = new gilded_rose.Item('Aged Brie', 2, 0)
		assertItem(item, 1, 1)
	})
	it('should decrease sell_in by 1 and quality by 1 for normal items again',function() {
		var item = new gilded_rose.Item('Elixir of the Mongoose', 5, 7)
		assertItem(item, 4, 6)
	})
	it('should leave sulfuras unchanged',function() {
		var item = new gilded_rose.Item('Sulfuras, Hand of Ragnaros', 0, 80)
		assertItem(item, 0, 80)
	})
	it('should leave sulfuras unchanged, even if negative',function() {
		var item = new gilded_rose.Item('Sulfuras, Hand of Ragnaros', -1, 80)
		assertItem(item, -1, 80)
	})
	it('should increase quality by 1 for concert 15 days out',function() {
		var item = new gilded_rose.Item('Backstage passes to a TAFKAL80ETC concert', 15, 20)
		assertItem(item, 14, 21)
	})
	it('should increase quality by 2 for concert 10 days out',function() {
		var item = new gilded_rose.Item('Backstage passes to a TAFKAL80ETC concert', 10, 45)
		assertItem(item, 9, 47)
	})
	it('should increase quality by 3 for concert 5 days out',function() {
		var item = new gilded_rose.Item('Backstage passes to a TAFKAL80ETC concert', 5, 45)
		assertItem(item, 4, 48)
	})
	it('should decrase mana cake twice as fast',function() {
		var item = new gilded_rose.Item('Conjured Mana Cake', 3, 6)
		assertItem(item, 2, 4)
	})
})