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
class ResourceEntity3Test {

    @Autowired
    private ResourceEntity3Repository repo;

    @Test
    @DisplayName("Batch insert resource")
    public void testBatchInsert() {

        reset();
        ResourceEntity3 parent = new ResourceEntity3("p");

        parent = createChildren(parent, 10);

        repo.save(parent);

        //forces the SQL to be executed
        TestTransaction.flagForCommit();
        TestTransaction.end();

        assertInsertCount(2);
        assertSelectCount(parent.getChildren().size() + 1);
    }

    public ResourceEntity3 createChildren(final ResourceEntity3 parent, final int numChildren) {
        for(int i=0; i < numChildren; i++) {
            ChildEntity3 child = new ChildEntity3(parent.getId() + "child" + "-" + i);
            child.setParentId(parent.getId());
            parent.getChildren().add(child);
        }
        return parent;
    }

}