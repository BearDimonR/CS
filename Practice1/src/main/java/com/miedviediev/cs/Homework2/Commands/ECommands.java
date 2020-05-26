package com.miedviediev.cs.Homework2.Commands;

public enum ECommands {
    AMOUNT_OF_PRODUCT(new AmountProductCommand()),
    REDUCE_AMOUNT_OF_PRODUCT(new ReduceAmountProductCommand()),
    ADD_AMOUNT_TO_PRODUCT(new AddAmountProductCommand()),
    ADD_GROUP(new AddGroupCommand()),
    ADD_GROUP_PRODUCT_TO_PRODUCT(new AddGroupProductCommand()),
    SET_PRICE_TO_PRODUCT(new PriceSetCommand());

    private final ICommand command;
    private final int id;

    public ICommand getCommand() {
        return command;
    }
    public int getId() { return id; }
    public static ECommands getElem(int id) { return ECommands.values()[id]; }
    public static ICommand getCommandById(int id) { return getElem(id).command; }

    ECommands(ICommand command) {
        this.id = ordinal();
        this.command = command;
    }

    @Override
    public String toString() {
        return "ECommands {" +
                "command=" + command +
                ", id=" + id +
                '}';
    }
}
