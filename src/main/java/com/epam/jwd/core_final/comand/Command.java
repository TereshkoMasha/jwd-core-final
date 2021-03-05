package com.epam.jwd.core_final.comand;

import com.epam.jwd.core_final.exception.InvalidStateException;

import java.io.IOException;

public interface Command {
    void execute() throws IOException, InvalidStateException;
}
