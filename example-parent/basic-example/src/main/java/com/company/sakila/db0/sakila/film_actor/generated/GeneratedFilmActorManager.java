package com.company.sakila.db0.sakila.film_actor.generated;

import com.company.sakila.db0.sakila.film_actor.FilmActor;
import com.speedment.common.annotation.GeneratedCode;
import com.speedment.runtime.config.identifier.TableIdentifier;
import com.speedment.runtime.core.manager.Manager;
import com.speedment.runtime.field.Field;
import java.util.List;
import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

/**
 * The generated base interface for the manager of every {@link
 * com.company.sakila.db0.sakila.film_actor.FilmActor} entity.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made to
 * it will be overwritten.
 * 
 * @author Speedment
 */
@GeneratedCode("Speedment")
public interface GeneratedFilmActorManager extends Manager<FilmActor> {
    
    TableIdentifier<FilmActor> IDENTIFIER = TableIdentifier.of(
        "db0",
        "sakila",
        "film_actor"
    )
    ;
    List<Field<FilmActor>> FIELDS = unmodifiableList(asList(
        FilmActor.ACTOR_ID,
        FilmActor.FILM_ID,
        FilmActor.LAST_UPDATE
    ));
    
    @Override
    default Class<FilmActor> getEntityClass() {
        return FilmActor.class;
    }
}