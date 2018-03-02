package com.company.sakila.db0.sakila.category.generated;

import com.company.sakila.db0.sakila.category.Category;
import com.company.sakila.db0.sakila.category.CategoryImpl;
import com.speedment.common.annotation.GeneratedCode;
import com.speedment.runtime.config.identifier.TableIdentifier;
import com.speedment.runtime.core.component.SqlAdapter;
import com.speedment.runtime.core.db.SqlFunction;
import com.speedment.runtime.core.exception.SpeedmentException;
import java.sql.ResultSet;
import java.sql.SQLException;
import static com.speedment.common.injector.State.RESOLVED;

/**
 * The generated Sql Adapter for a {@link
 * com.company.sakila.db0.sakila.category.Category} entity.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made to
 * it will be overwritten.
 * 
 * @author Speedment
 */
@GeneratedCode("Speedment")
public abstract class GeneratedCategorySqlAdapter implements SqlAdapter<Category> {
    
    private final TableIdentifier<Category> tableIdentifier;
    
    protected GeneratedCategorySqlAdapter() {
        this.tableIdentifier = TableIdentifier.of("db0", "sakila", "category");
    }
    
    protected Category apply(ResultSet resultSet, int offset) throws SpeedmentException {
        final Category entity = createEntity();
        try {
            entity.setCategoryId( resultSet.getShort(1 + offset)     );
            entity.setName(       resultSet.getString(2 + offset)    );
            entity.setLastUpdate( resultSet.getTimestamp(3 + offset) );
        } catch (final SQLException sqle) {
            throw new SpeedmentException(sqle);
        }
        return entity;
    }
    
    protected CategoryImpl createEntity() {
        return new CategoryImpl();
    }
    
    @Override
    public TableIdentifier<Category> identifier() {
        return tableIdentifier;
    }
    
    @Override
    public SqlFunction<ResultSet, Category> entityMapper() {
        return entityMapper(0);
    }
    
    @Override
    public SqlFunction<ResultSet, Category> entityMapper(int offset) {
        return rs -> apply(rs, offset);
    }
}