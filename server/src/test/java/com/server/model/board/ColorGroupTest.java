package com.server.model.board;

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;
import com.communication.board.ColorGroupDTO;
import com.communication.gamelogic.PlayerDTO;
import com.communication.values.BonusType;
import com.communication.values.CityColor;

public class ColorGroupTest {
	
	String[] close;
	String[] close2;
	Bonus[] b;
	City city;
	City city2;
	


	
	@Before
	public void setUp()
		throws Exception {
		close = new String[1];
		close[0]= "Castrum";
		close2 = new String[3];
		close2[0] = "Dorful";
		close2[1] = "Framek";
		close2[2] = "Arkon";
		b = new Bonus[1]; 
		b[0] = new Bonus(BonusType.POINT, 1);
		city = new City("Arkon", CityColor.BLUE, close, 3, new BonusToken(b) );
		city2 = new City("Castrum", CityColor.BLUE, close2,3,new BonusToken(b));
		
		
	}
	
	
	@Test
	public void testColorGroup_1()
		throws Exception {
		CityColor col = CityColor.BLUE;

		ColorGroup result = new ColorGroup(col);

		assertNotNull(result);
		assertEquals(null, result.getBonus());
	}
	
	@Test(expected=NullPointerException.class)
	public void theConstructorCannotBeUncoloured(){
		
		ColorGroup colGroup= new ColorGroup(null);
		
	}

	@Test(expected=NullPointerException.class)
	public void anAddedCityCannotBeNull()
		throws Exception {
		ColorGroup fixture = new ColorGroup(CityColor.BLUE);
		fixture.setBonus(new BonusCard(new Bonus(BonusType.POINT, 1)));
		fixture.addCity(null);
		

	}

	@Test
	public void testGetBonus()
		throws Exception {
		ColorGroup colGroup = new ColorGroup(CityColor.BLUE);
		
		Bonus b= new Bonus(BonusType.POINT, 1);
		BonusCard bc = new BonusCard(b);
		colGroup.setBonus(bc);
		
	

		BonusCard result = colGroup.getBonus();
		
		assertEquals(bc,result);
		

	}
	
	@Test(expected=NullPointerException.class)
	public void testYouCannotSetANullBonus(){
		ColorGroup colG = new ColorGroup(CityColor.YELLOW);
		colG.setBonus(null);
	}

	@Test
	public void testGetCity()
		throws Exception {
		ColorGroup colGroup  = new ColorGroup(CityColor.BLUE);
		colGroup.setBonus(new BonusCard(new Bonus(BonusType.POINT, 1)));
		colGroup.addCity(city);
		colGroup.addCity(city2);

		ArrayList<City> result = colGroup.getCity();

		assertNotNull(result);
		assertEquals(2, result.size());
	}

	@Test
	public void testGetColor_1()
		throws Exception {
		ColorGroup colGroup = new ColorGroup(CityColor.BLUE);
		CityColor result = colGroup.getColor();
		assertEquals("BLUE", result.name());
		assertEquals("BLUE", result.toString());
		
	}


	@Test
	public void testToDTO_1()
		throws Exception {
		ColorGroup fixture = new ColorGroup(CityColor.BLUE);
		fixture.setBonus(new BonusCard(new Bonus(BonusType.ASSISTANT, 1)));
		fixture.addCity(city);
		fixture.addCity(city2);

		ColorGroupDTO result = fixture.toDTO(new ArrayList<PlayerDTO>());

		
		assertNotNull(result);
		assertTrue (result instanceof ColorGroupDTO);
	}



	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(ColorGroupTest.class);
	}
}