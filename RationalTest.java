import junit.framework.TestCase;


public class RationalTest extends TestCase {
	
    protected Rational HALF;
    Rational r1, r2,r3; 
    protected void setUp() {
      HALF = new Rational( 1, 2 );
      r1 = new Rational(-5, 4);
	  r2 = new Rational(2, -3);
	  r3 =new Rational (0,1);
	  
    }

    // Create new test
    public RationalTest (String name) {
        super(name);
    }

    public void testEquality() {
        assertEquals(new Rational(1,3), new Rational(1,3));
        assertEquals(new Rational(1,3), new Rational(2,6));
        assertEquals(new Rational(3,3), new Rational(1,1));
        assertEquals(new Rational(5,-4), new Rational(-5,4));
        assertEquals(new Rational(-5,-4), new Rational(5,4));
    }
    
 // Test that the "equals" method works properly
 	public void testString()
 	{
 		assertTrue(r1.toString().equals("-6"));
 	}
 	
    // Test for nonequality
    public void testNonEquality() {
        assertFalse(new Rational(2,3).equals(
            new Rational(1,3)));
    }

    public void testAccessors() {
    	assertEquals(new Rational(2,3).numerator(), 2);
    	assertEquals(new Rational(2,3).denominator(), 3);
    }

    public void testRoot() {
        Rational s = new Rational( 1, 4 );
        Rational sRoot = null;
        try {
            sRoot = s.root();
        } catch (IllegalArgumentToSquareRootException e) {
            e.printStackTrace();
        }
        assertTrue( sRoot.isLessThan( HALF.plus( Rational.getTolerance() ) ) 
                        && HALF.minus( Rational.getTolerance() ).isLessThan( sRoot ) );
    }

    public void testAdic() {
	
		assertEquals(new Rational(1073741789, 20).plus(new Rational(1073741789, 30)), new Rational(1073741789,12));
		assertEquals(r1.plus(r2), new Rational(-23,12));
		
	}
    public void testOverflow() {
    	Rational a= new Rational(1,2147483548);
    	Rational b= new Rational(1,2);
    	assertEquals(a.plus(b).denominator()/2,2147483548);
    	
		assertEquals(new Rational(1073741789, 20).plus(new Rational(1073741789, 30)), new Rational(1073741789,12));
		assertEquals(r1.plus(r2), new Rational(-23,12));
		
	}
    
    public void testNonZeroDen() {
        assertTrue( new Rational(1,0).denominator() != 0 );
       	
	}
    public void testResulZero() {
        assertEquals( new Rational(0,1).times(new Rational(12,23)), new Rational(0,6) );
        assertEquals( new Rational(0,1).plus(new Rational(12,23)), new Rational(12,23) );
        assertEquals( new Rational(0,1).divides(new Rational(12,23)), new Rational(0,6) );
        assertEquals( new Rational(0,1).minus(new Rational(12,23)), new Rational(-12,23) );
	}
    public void testResulOne() {
        assertEquals( new Rational(1,1), new Rational(9,9) );
       	
	}
    public void testResta() {
    		assertEquals(new Rational( 1,6).minus(new Rational(-4, -8)), new Rational(-1,3));
	
	}
    public void testproduct() {
    	
		assertEquals(new Rational(4, 17).times(new Rational(17, 4)), new Rational(1,1));
		
	}
    public void testDiv() {
    	
		assertEquals(new Rational(0, 10).divides(new Rational(4, 3)), new Rational(0,1));
	}
    public void testNegDen() {
    	
    	  assertEquals(new Rational(5,-4), new Rational(-5,4));
		
	}
    public void testIslessThan() {
    	Rational s= new Rational(2,3);
    	Rational k= new Rational(5,6);
    	  assertEquals(s.isLessThan(k),true);
    	  	
  	  
		
	}
    
    public void testAbs() {
    	Rational s= new Rational(-5,15);
    	Rational v= new Rational(5,15);    	
    	Rational k= s.abs();
    	  assertEquals(k.numerator(),v.numerator());
    	  assertEquals(k.denominator(),v.denominator());
    	  
  		
  	}
      
    public static void main(String args[]) {
        String[] testCaseName = 
            { RationalTest.class.getName() };
        // junit.swingui.TestRunner.main(testCaseName);
        junit.textui.TestRunner.main(testCaseName);
    }
}
