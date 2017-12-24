import java.util.*;

public class q57_amida1
{
private static final int N = 7; //縦線
private static final int M = 10; //横線

private static Set< String > data1 = new HashSet<>(); //M未満でも作成可能な結果
private static Set< String > data2 = new HashSet<>(); //Mで作成された結果
private static Set< List<Integer> > contextSet = new HashSet<>(); //context全パターン

public static void printIntArray( int[] array )
{
	for( int i = 0; i < array.length; ++i )
	{
		if( i > 0 )
		{
			System.out.print( ", " );
		}
		System.out.print( array[ i ] );
	}
	System.out.println();
}

public static void amida2( final int depth, final int lastSelected, final List<Integer> context )
{
	//今回何を選ぶか
	for( int n = 0; n < N -1; ++n )
	{
		List<Integer> newContext = new ArrayList<>();
		newContext.addAll( context );
		//選んだものをcontextに追加する		
		newContext.add( n );
		
		if( depth == M - 1 )
		{
			onComplete( newContext );
		}
		else
		{
			amida2( depth + 1, n, newContext );
		}
	}	
}

public static void p( Object o )
{
	System.out.println( o );
}

public static void main( String[] args ) throws Exception
{	
	amida2( 0, -1, new ArrayList<Integer>() );
	reduce();	
}

public static void onComplete( final List<Integer> context )
{
	contextSet.add( context );
}

public static String intArrayToString( final int[] array )
{
	StringBuffer buf = new StringBuffer();
	for( int i : array )
	{
		if( buf.length() > 0 )
		{
			buf.append( ',' );
		}
		buf.append( i );
	}
	return buf.toString();
}

public static void reduce()
{
	for( List<Integer> context : contextSet )
	{
		int[] start = new int[ N ];
		for( int i = 0; i < N; ++i )
		{
			start[ i ] = i + 1;
		}
		
		for( int i = 0; i < M -1; ++i )
		{
			Integer value = context.get( i );
			
				//入れ替え
			int i1 = start[ value ];
			int i2 = start[ value + 1 ];
			start[ value ] = i2;
			start[ value + 1 ] = i1;
			
			data1.add( intArrayToString( start ) );
		}		
	}
	
	p( data1.size() );

	for( List<Integer> context : contextSet )
	{
		int[] start = new int[ N ];
		for( int i = 0; i < N; ++i )
		{
			start[ i ] = i + 1;
		}
		
		for( int i = 0; i < M; ++i )
		{
			Integer value = context.get( i );
			
				//入れ替え
			int i1 = start[ value ];
			int i2 = start[ value + 1 ];
			start[ value ] = i2;
			start[ value + 1 ] = i1;
		}		
		
		final String str = intArrayToString( start );
		if( !data1.contains( str ) )
		{
			p( str + " " + context );
			data2.add( str );
		}
		else
		{
		}
	}
	p( "=======================");
	
	for( String result : data2 )
	{
		p( result );
	}
	
	p( data2.size() );

}

}