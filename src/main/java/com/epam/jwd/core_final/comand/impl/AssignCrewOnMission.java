package com.epam.jwd.core_final.comand.impl;

import com.epam.jwd.core_final.comand.Command;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;

import java.util.Optional;
import java.util.Scanner;

public class AssignCrewOnMission implements Command {
    Scanner scanner = new Scanner(System.in);

    @Override
    public void execute() {
        CrewMemberCriteria.CrewMemberCriteriaBuilder builder = new CrewMemberCriteria.CrewMemberCriteriaBuilder();
        System.out.println("\nLet's find the right crew");
        System.out.print("Enter all crew criteria: ");
        System.out.println("(you can find crew by)\n" +
                "1. Name\n" +
                "2. ID\n" +
                "3. Rank\n" +
                "4. Role\n");
        int inx = 0;
        while (inx != -1) {
            System.out.print("Enter command (enter 1-4 to input criteria, 0 - to end input) : ");
            switch (scanner.nextInt()) {
                case 1: {
                    System.out.print("Input name: ");
                    String name = scanner.next();
                    String surname = scanner.next();
                    builder.setName(name + " " + surname);
                    break;
                }
                case 2: {
                    System.out.print("Input id: ");
                    Long id = scanner.nextLong();
                    builder.setId(id);
                    break;
                }
                case 3: {
                    System.out.print("Rank (1-4) : ");
                    int id = scanner.nextInt();
                    Rank rank = Rank.resolveRankById(id);
                    builder.setRank(rank);
                    break;
                }
                case 4: {
                    System.out.print("Role (1-4) : ");
                    int id = scanner.nextInt();
                    Role role = Role.resolveRoleById(id);
                    builder.setRole(role);
                    break;
                }
                case 0: {
                    inx = -1;
                }
            }
        }
        CrewMemberCriteria criteria = builder.build();
        Optional<CrewMember> crew = CrewServiceImpl.instance.findCrewMemberByCriteria(criteria);
        if (crew.isPresent()) {
            crew.ifPresent(value -> MissionServiceImpl.getInstance().assignCrewMemberOnMission(value));
        } else {
            System.out.println("The crew was not found according to these criteria");
        }
    }
}
