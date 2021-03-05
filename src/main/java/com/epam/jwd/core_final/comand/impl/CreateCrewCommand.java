package com.epam.jwd.core_final.comand.impl;

import com.epam.jwd.core_final.comand.Command;
import com.epam.jwd.core_final.context.impl.NasaContext;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;

import java.io.IOException;
import java.util.Scanner;

public class CreateCrewCommand implements Command {
    Scanner scanner = new Scanner(System.in);
    CrewServiceImpl service;

    @Override
    public void execute() throws IOException {

        System.out.println("Enter crew name: ");
        String name = scanner.next();
        System.out.println("Enter crew rank");
        Rank rank = Rank.resolveRankById(Integer.parseInt(scanner.next()));
        System.out.println("Enter crew role");
        Role role = Role.resolveRoleById(scanner.nextInt());
        CrewMember crewMember = service.createCrewMember(new CrewMemberFactory().create(role, name, rank));

        NasaContext.getInstance().retrieveBaseEntityList(CrewMember.class).add(crewMember);
    }
}
