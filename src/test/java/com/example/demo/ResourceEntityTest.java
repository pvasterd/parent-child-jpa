package com.example.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.transaction.TestTransaction;

import static io.hypersistence.utils.jdbc.validator.SQLStatementCountValidator.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import(TestConfig.class)
class ResourceEntityTest {

    @Autowired
    private ResourceEntityRepository repo;

    @Test
    @DisplayName("Batch insert resource")
    public void testBatchInsert() {

        reset();

        ResourceEntity parent = new ResourceEntity("p");

        parent = createChildren(parent, 10);

        repo.save(parent);

        //forces the SQL to be executed
        TestTransaction.flagForCommit();
        TestTransaction.end();

        assertInsertCount(1);
        assertSelectCount(1);
    }

    public ResourceEntity createChildren(final ResourceEntity parent, final int numChildren) {
        for(int i=0; i < numChildren; i++) {
            ResourceEntity child = new ResourceEntity(parent.getId() + "child" + "-" + i);
            child.setParent(parent);
            parent.getChildren().add(child);
        }
        return parent;
    }

}