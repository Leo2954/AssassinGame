import java.util.List;

public class AssassinManager {
    // YOUR CODE GOES HERE
    private AssassinNode ringFront;
    private AssassinNode graveFront;

    public AssassinManager(List<String> names)
    {
        if(names!=null||!names.isEmpty())
        {
            for(int i=names.size()-1; i>-1; i--)
            {
                ringFront = new AssassinNode(names.get(i), ringFront);
            }
        }
        else
        {
            throw new IllegalArgumentException("List is empty or null");
        }
    }

    public String killRing()
    {
        String ring = "";
        AssassinNode temp = ringFront;
        while(temp.next!=null)
        {
            ring = ring + ">> " + temp.name + " is stalking " + temp.next.name + "\n";
            temp=temp.next;
        }
        ring = ring + ">> " + temp.name + " is stalking " + ringFront.name;
        return ring;
    }

    public String graveyard()
    {
        if(graveFront == null)
        {
            return "The graveyard is empty";

        }
        String grave = "";
        AssassinNode current = graveFront;

            while(current!=null)
            {
                grave = grave + "\n" + current.name + " was killed by " + current.killer;
                current=current.next;
            }
        return grave;
    }



    public boolean killRingContains(String name)
    {
        AssassinNode temp = ringFront;
        while(temp!=null)
        {
            if(temp.name.equalsIgnoreCase(name))
            {
                return true;
            }
            temp=temp.next;
        }
        return false;
    }

    public boolean graveyardContains(String name)
    {
        if(graveFront==null){
            return false;
        }
        AssassinNode temp = graveFront;
        while(temp!=null)
        {
            if(temp.name.equalsIgnoreCase(name))
            {
                return true;
            }
            temp=temp.next;
        }
        return false;

    }

    public void kill(String name)
    {

        if(isGameOver()&&!killRingContains(name))
        {
            throw new IllegalStateException("Game is over");
        }
        else if(isGameOver())
        {
            throw new IllegalStateException("Game is over");
        }
        else if(!killRingContains(name))
        {
            throw new IllegalArgumentException("Person is not in kill ring");
        }
        else {
            AssassinNode ringCurrent = ringFront;
            AssassinNode ringPrevious = null;
            if (ringCurrent.name.equalsIgnoreCase(name)) {
                graveFront = new AssassinNode(ringCurrent.name, graveFront);
                while(ringCurrent!=null)
                {
                    ringPrevious = ringCurrent;
                    ringCurrent = ringCurrent.next;
                }
                graveFront.killer=ringPrevious.name;
                ringFront=ringFront.next;

            }
            else {
                while (ringCurrent != null && !ringCurrent.name.equalsIgnoreCase(name)) {
                    ringPrevious = ringCurrent;
                    ringCurrent = ringCurrent.next;
                }

                graveFront = new AssassinNode(ringCurrent.name, graveFront);
                graveFront.killer = ringPrevious.name;
                ringPrevious.next = ringCurrent.next;


            }
        }
    }





    public boolean isGameOver()
    {
        return(ringFront.next==null);
    }

    public String winner()
    {
        if(isGameOver())
        {
            return ringFront.name;
        }
        return null;
    }









}
