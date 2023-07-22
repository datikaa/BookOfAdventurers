package com.datikaa.charlatan.core.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.datikaa.charlatan.core.database.entity.AbilityEntity;
import com.datikaa.charlatan.core.database.partial.AbilityEntityPartialUpdate;
import java.lang.Class;
import java.lang.Exception;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AbilityDao_Impl implements AbilityDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AbilityEntity> __insertionAdapterOfAttributeEntity;

  private final EntityDeletionOrUpdateAdapter<AbilityEntityPartialUpdate> __updateAdapterOfAttributeEntityPartialUpdateAsAttributeEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAttributes;

  public AbilityDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAttributeEntity = new EntityInsertionAdapter<AbilityEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `AttributeEntity` (`id`,`characterId`,`type`,`value`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, AbilityEntity value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getCharacterId());
        if (value.getType() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, __Type_enumToString(value.getType()));
        }
        stmt.bindLong(4, value.getValue());
      }
    };
    this.__updateAdapterOfAttributeEntityPartialUpdateAsAttributeEntity = new EntityDeletionOrUpdateAdapter<AbilityEntityPartialUpdate>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `AttributeEntity` SET `id` = ?,`value` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, AbilityEntityPartialUpdate value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getValue());
        stmt.bindLong(3, value.getId());
      }
    };
    this.__preparedStmtOfDeleteAttributes = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM AttributeEntity";
        return _query;
      }
    };
  }

  @Override
  public Object insertAbility(final AbilityEntity abilityEntity,
                              final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAttributeEntity.insert(abilityEntity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object updateAbility(final AbilityEntityPartialUpdate abilityEntityPartialUpdate,
                              final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfAttributeEntityPartialUpdateAsAttributeEntity.handle(abilityEntityPartialUpdate);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteAllAbilities(final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAttributes.acquire();
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteAttributes.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Object getAbility(final int characterId, final AbilityEntity.Type type,
                           final Continuation<? super AbilityEntity> continuation) {
    final String _sql = "SELECT * FROM AttributeEntity WHERE characterId = ? AND type = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, characterId);
    _argIndex = 2;
    if (type == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, __Type_enumToString(type));
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<AbilityEntity>() {
      @Override
      public AbilityEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCharacterId = CursorUtil.getColumnIndexOrThrow(_cursor, "characterId");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfValue = CursorUtil.getColumnIndexOrThrow(_cursor, "value");
          final AbilityEntity _result;
          if(_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpCharacterId;
            _tmpCharacterId = _cursor.getInt(_cursorIndexOfCharacterId);
            final AbilityEntity.Type _tmpType;
            _tmpType = __Type_stringToEnum(_cursor.getString(_cursorIndexOfType));
            final int _tmpValue;
            _tmpValue = _cursor.getInt(_cursorIndexOfValue);
            _result = new AbilityEntity(_tmpId,_tmpCharacterId,_tmpType,_tmpValue);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, continuation);
  }

  @Override
  public Flow<List<AbilityEntity>> flowAbilities() {
    final String _sql = "SELECT * FROM AttributeEntity";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"AttributeEntity"}, new Callable<List<AbilityEntity>>() {
      @Override
      public List<AbilityEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCharacterId = CursorUtil.getColumnIndexOrThrow(_cursor, "characterId");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfValue = CursorUtil.getColumnIndexOrThrow(_cursor, "value");
          final List<AbilityEntity> _result = new ArrayList<AbilityEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final AbilityEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpCharacterId;
            _tmpCharacterId = _cursor.getInt(_cursorIndexOfCharacterId);
            final AbilityEntity.Type _tmpType;
            _tmpType = __Type_stringToEnum(_cursor.getString(_cursorIndexOfType));
            final int _tmpValue;
            _tmpValue = _cursor.getInt(_cursorIndexOfValue);
            _item = new AbilityEntity(_tmpId,_tmpCharacterId,_tmpType,_tmpValue);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private String __Type_enumToString(final AbilityEntity.Type _value) {
    if (_value == null) {
      return null;
    } switch (_value) {
      case Strength: return "Strength";
      case Dexterity: return "Dexterity";
      case Constitution: return "Constitution";
      case Intelligence: return "Intelligence";
      case Wisdom: return "Wisdom";
      case Charisma: return "Charisma";
      default: throw new IllegalArgumentException("Can't convert enum to string, unknown enum value: " + _value);
    }
  }

  private AbilityEntity.Type __Type_stringToEnum(final String _value) {
    if (_value == null) {
      return null;
    } switch (_value) {
      case "Strength": return AbilityEntity.Type.Strength;
      case "Dexterity": return AbilityEntity.Type.Dexterity;
      case "Constitution": return AbilityEntity.Type.Constitution;
      case "Intelligence": return AbilityEntity.Type.Intelligence;
      case "Wisdom": return AbilityEntity.Type.Wisdom;
      case "Charisma": return AbilityEntity.Type.Charisma;
      default: throw new IllegalArgumentException("Can't convert value to enum, unknown value: " + _value);
    }
  }
}
