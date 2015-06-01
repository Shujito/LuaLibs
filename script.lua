return function()
	local ww = Person
	local sumireko = ww:new():name('Sumireko'):lastname('Usami'):age(15)
	local sanae = Person:new():name('Sanae'):lastname('Kochiya'):age(17)	
	print(sumireko:greet())
	print(sanae:greet())
	print(sumireko:meet(sanae))
	print(sanae:meet(sumireko))
	print(sanae:meet(sanae))
	print(sumireko:meet(sumireko))
end
