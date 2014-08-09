package lvnghiem.app.core.database;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import lvnghiem.app.core.application.NghiemBaseApp;
import lvnghiem.app.core.utils.Logger;

import nghiem.app.gen.R;

import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper<Data, Id> extends OrmLiteSqliteOpenHelper
{
	private static final Logger LOGGER = new Logger(DatabaseHelper.class);
	private static final String DB_NAME = NghiemBaseApp.getInstance().getString(R.string.settings_database_name) + ".db";
	private static final int DB_VERSION = NghiemBaseApp.getInstance().getResources().getInteger(R.integer.settings_database_version);

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
			TableUtils.createTableIfNotExists(connectionSource, mClass);
		}
		catch (SQLException e)
		{
			LOGGER.debug("Can't create database", e);
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

	public List<Data> getAll() throws SQLException
	{
		return getDao().queryForAll();
	}

	public Data get(Id id) throws SQLException
	{
		return getDao().queryForId(id);
	}

    public int insert(Data item) throws SQLException
    {
        return getDao().create(item);
    }

	public void insert(Collection<Data> collection) throws SQLException
	{
		insert(collection, false);
	}

	public void insert(Collection<Data> collection, boolean clearAll) throws SQLException
	{
		if (clearAll)
		{
			clear();
		}
		for (Data item : collection)
        {
            insert(item);
        }
	}

	public int update(Data item) throws SQLException
	{
		return getDao().update(item);
	}
    
    public void update(Collection<Data> collection) throws SQLException
    {
        for (Data item : collection)
        {
            update(item);
        }
    }

	public int deleteById(Id id) throws SQLException
	{
		return getDao().deleteById(id);
	}

	public int delete(Data item) throws SQLException
	{
		return getDao().delete(item);
	}

	public int delete(Collection<Data> collection) throws SQLException
	{
		return getDao().delete(collection);
	}

	public int clear() throws SQLException
	{
		return getDao().delete(getAll());
	}
}
