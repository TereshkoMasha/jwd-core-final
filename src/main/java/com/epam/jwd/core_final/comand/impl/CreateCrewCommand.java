package com.epam.jwd.core_final.comand.impl;

import com.epam.jwd.core_final.comand.Command;
import com.epam.jwd.core_final.context.impl.NasaContext;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;

import java.util.Scanner;

public class CreateCrewCommand implements Command {
    Scanner scanner = new Scanner(System.in);


    @Override
    public void execute() {
        try {
            System.out.print("\nEnter crew name: ");
            String name = scanner.next();
            System.out.print("Enter crew rank");
            Rank rank = Rank.resolveRankById(Integer.parseInt(scanner.next()));
            System.out.print("Enter crew role");
            Role role = Role.resolveRoleById(scanner.nextInt());
            CrewMember crewMember = CrewServiceImpl.getInstance().createCrewMember(new CrewMemberFactory().create(role, name, rank));

            NasaContext.getInstance().retrieveBaseEntityList(CrewMember.class).add(crewMember);
            System.out.println("Success: crew " + name + " added\n");

        } catch (Exception e) {
            System.out.println("Invalid input, try again");
        }
    }
}
