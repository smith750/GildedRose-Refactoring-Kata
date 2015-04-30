var assert = require("assert")

describe("update_quality()",function() {
	it('should decreate sellIn by 1 and quality by 1 for normal items',function() {
		var item = new Item('+5 Dexterity Vest', 10, 20)
		items.push(item);
		assert.equal(9, item.sellIn)
		assert.equal(19, item.quality)
		items.pop(item);
	})
})