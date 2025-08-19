Spring’s @Transactional and TransactionTemplate by default only rollback on unchecked exceptions.
Checked exceptions won’t trigger rollback unless explicitly configured (rollbackFor = ...).