local function main()
	local sumireko = Person:new():name('Sumireko'):lastname('Usami'):age(15)
	local sanae = Person:new():name('Sanae'):lastname('Kochiya'):age(17)	
	print(sumireko:greet())
	print(sanae:greet())
	print(sumireko:meet(sanae))
	print(sanae:meet(sumireko))
	print(sanae:meet(sanae))
	print(sumireko:meet(sumireko))
end

local function metas()
	local reimu = Person:new():name('Reimu'):lastname('Hakurei'):age(18)
	local sanae = Person:new():name('Sanae'):lastname('Kochiya'):age(17)
	--print(reimu:greet())
	--print(sanae:greet())
	reimu.hakurei = reimu
	sanae.kochiya = sanae
	print(reimu.hakurei:greet())
	print(sanae.kochiya:greet())
	print(sanae.hakurei)
	print(reimu.kochiya)
end

local function touhous()
	local reimu = Person:new():name('Reimu'):lastname('Hakurei'):age(18)
	print(reimu:greet())
	print(reimu.greet)
	reimu.greet = 'something else'
	print(reimu.greet)
	local sanae = Person:new():name('Sanae'):lastname('Kochiya'):age(17)
	print(sanae:greet())
	print(sanae.greet)
	sanae.greet = 'another thing'
	print(sanae.greet)
	local sumireko = Person:new():name('Sumireko'):lastname('Usami'):age(15)
	print(sumireko:greet())
	print(sumireko.greet(reimu))
	print(sumireko.greet(sanae))
	print(sumireko:meet(sanae))
	print(sumireko.meet(sanae,sumireko))
end

local function methods()
	print(Person.Methods.greet)
	Person.Methods.greet = 'hijack!'
	print(Person.Methods.greet)
	local reimu = Person:new():name('Reimu'):lastname('Hakurei'):age(18)
	print(reimu.greet)
end

return function()
	print '== main ============================================================'
	main()
	print '== metas ==========================================================='
	metas()
	print '== touhous ========================================================='
	touhous()
	print '== methods ========================================================='
	methods()
end