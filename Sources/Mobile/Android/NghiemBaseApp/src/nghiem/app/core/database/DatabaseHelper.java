package nghiem.app.core.database;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import nghiem.app.core.application.NghiemBaseApp;
import nghiem.app.gen.R;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper<Data, Id> extends OrmLiteSqliteOpenHelper
{
	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);
	private static final String DB_NAME = NghiemBaseApp.getInstance().getString(R.string.database_name) + ".db";
	private static final int DB_VERSION = Integer.parseInt(NghiemBaseApp.getInstance().getString(R.string.database_version));

	private Dao<Data, Id> mDao = null;
	private Class<Data> mClass = null;

	public DatabaseHelper(Class<Data> clazz)
	{
		super(NghiemBaseApp.getInstance().getApplicationContext(), DB_NAME, null, DB_VERSION);
		mClass = clazz;
	}

	public Dao<Data, Id> getDao() throws SQLException
	{
		if (mDao == null)
		{
			mDao = getDao(mClass);
		}
		return mDao;
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource)
	{
		try
		{
			LOGGER.debug("onCreate");
			TableUtils.createTable(connectionSource, mClass);
		}
		catch (SQLException e)
		{
			LOGGER.error("Can't create database", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion)
	{
		try
		{
			LOGGER.debug("onUpgrade");
			TableUtils.dropTable(connectionSource, mClass, true);
			onCreate(db, connectionSource);
		}
		catch (SQLException e)
		{
			LOGGER.error("Can't drop databases", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void close()
	{
		super.close();
		mDao = null;
	}

	public List<Data> get() throws SQLException
	{
		return getDao().queryForAll();
	}

	public Data get(Id id) throws SQLException
	{
		return getDao().queryForId(id);
	}

	public void insert(Collection<Data> datas) throws SQLException
	{
		for (Data data : datas)
		{
			try
			{
				getDao().create(data);
			}
			catch (SQLException e)
			{
				throw new SQLException("Failed to insert " + mClass.getSimpleName());
			}
		}
	}

	public void insert(Collection<Data> datas, boolean clearAll) throws SQLException
	{
		if (clearAll)
		{
			clear();
		}
		insert(datas);
	}

	public int insert(Data data) throws SQLException
	{
		return getDao().create(data);
	}

	public int update(Data data) throws SQLException
	{
		return getDao().update(data);
	}

	public int deleteById(Id id) throws SQLException
	{
		return getDao().deleteById(id);
	}

	public int delete(Data data) throws SQLException
	{
		return getDao().delete(data);
	}

	public int delete(Collection<Data> datas) throws SQLException
	{
		return getDao().delete(datas);
	}

	public int clear() throws SQLException
	{
		return getDao().delete(get());
	}
}
