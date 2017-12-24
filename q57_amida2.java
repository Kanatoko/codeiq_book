import java.util.*;

public class q57_amida2
{
private static final int N = 7; //縦線
private static final int M = 10; //横線

private static int count;

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

public static void p( Object o )
{
	System.out.println( o );
}

public static void f2_2( final int[] context, final int[] lengthArray, final int selectedIndex, int remain )
{
	int movable = lengthArray[ selectedIndex ];
	int limit = movable;
	if( remain < limit )
	{
		limit = remain;
	}
	for( int m = 0; m <= limit; ++m )
	{
		if( m == 4 )
		{
			p( "" );
		}
		context[ selectedIndex ] = m;
		int _remain = remain;
		_remain -= m;
		if( _remain == 0 )
		{
			++count;

			printIntArray( context );

			//contextを掃除
			for( int s = selectedIndex; s < context.length; ++s )
			{
				context[ s ] = 0;
			}

			//return;
			continue;
		}
		if( selectedIndex < N - 1 )
		{
			f2_2( context, lengthArray, selectedIndex + 1, _remain );
		}
	}
}

public static void main( String[] args ) throws Exception
{
	int[] lengthArray = new int[ N ];//n番めの要素はどの程度右まで移動できるか
	for( int i = 0; i < lengthArray.length; ++i )
	{
		lengthArray[ i ] = N - i - 1;
	}
	printIntArray( lengthArray );

	final int[] context = new int[ N ];

	f2_2( context, lengthArray, 0, M );

	p( count );
}

}