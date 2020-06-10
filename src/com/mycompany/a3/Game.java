package com.mycompany.a3;

import java.util.Vector;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;

public class Game extends Form implements Runnable{
	private GameWorld gw;
	private MapView mv;
	private PointsView pv;
	private UITimer timer;
	private Vector<Command> keybindings = new Vector<Command>();
	private Vector<Button> disable = new Vector<Button>();
	private Button p = new MyButton("");
	private Button re = new MyButton("");
	private Button refuel = new MyButton("");
	
	public Game() {
		//GameWorld and Observer Instantiation
		gw = new GameWorld();
		mv = new MapView();
		pv = new PointsView();
		gw.addObserver(mv);
		gw.addObserver(pv);
		//Command Instantiation
		COAddAsteroid addasteroid = new COAddAsteroid(gw);
		COAddNonPlayerShip addnps = new COAddNonPlayerShip(gw);
		COAddSpaceStation addss = new COAddSpaceStation(gw);
		COAddPlayerShip addps = new COAddPlayerShip(gw);
		COAccelerate accelerate = new COAccelerate(gw);
		CODeccelerate deccelerate = new CODeccelerate(gw);
		COLeftPS left = new COLeftPS(gw);
		CORightPS right = new CORightPS(gw);
		COLeftML leftml = new COLeftML(gw);
		CORightML rightml = new CORightML(gw);
		COFireMissile psfire = new COFireMissile(gw);
		COFireMissileNPS npsfire = new COFireMissileNPS(gw);
		COJump jump = new COJump(gw);
		COReload reload = new COReload(gw);
		COTick tick = new COTick(gw);
		COPause pause = new COPause(gw,this);
		COResume resume = new COResume(gw,this);
		CORefuel refuelc = new CORefuel(gw);
		COQuit quit = new COQuit(gw);
		COAbout about = new COAbout(gw);
		CONew neu = new CONew(gw);
		COSave sav = new COSave(gw);
		COUndo und = new COUndo(gw);
		COToggleSound ts = new COToggleSound(gw);
		keybindings.add(accelerate);
		keybindings.add(deccelerate);
		keybindings.add(left);
		keybindings.add(right);
		keybindings.add(leftml);
		keybindings.add(rightml);
		keybindings.add(psfire);
		keybindings.add(jump);
		keybindings.add(quit);
		
		
		//Command Menu Setup
		Container commandmenu = new Container();
		commandmenu.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		commandmenu.setScrollableY(true);
		Button a = new MyButton("");
		a.setCommand(addasteroid);
		commandmenu.add(a);
		Button y = new MyButton("");
		y.setCommand(addnps);
		commandmenu.add(y);
		Button b = new MyButton("");
		b.setCommand(addss);
		commandmenu.add(b);
		Button s = new MyButton("");
		s.setCommand(addps);
		commandmenu.add(s);
		Button i = new MyButton("");
		i.setCommand(accelerate);
		commandmenu.add(i);
		disable.add(i);
		Button d = new MyButton("");
		d.setCommand(deccelerate);
		commandmenu.add(d);
		disable.add(d);
		Button l = new MyButton("");
		l.setCommand(left);
		commandmenu.add(l);
		disable.add(l);
		Button r = new MyButton("");
		r.setCommand(right);
		commandmenu.add(r);
		disable.add(r);
		Button lm = new MyButton("");
		lm.setCommand(leftml);
		commandmenu.add(lm);
		disable.add(lm);
		Button rm = new MyButton("");
		rm.setCommand(rightml);
		commandmenu.add(rm);
		disable.add(rm);
		Button f = new MyButton("");
		f.setCommand(psfire);
		commandmenu.add(f);
		disable.add(f);
		Button el = new MyButton("");
		el.setCommand(npsfire);
		commandmenu.add(el);
		disable.add(el);
		Button j = new MyButton("");
		j.setCommand(jump);
		commandmenu.add(j);
		disable.add(j);
		Button n = new MyButton("");
		n.setCommand(reload);
		commandmenu.add(n);
		Button t = new MyButton("");
		t.setCommand(tick);
		commandmenu.add(t);
		p.setCommand(pause);
		commandmenu.add(p);
		re.setCommand(resume);
		commandmenu.add(re);
		re.setEnabled(false);
		re.getAllStyles().setBgColor(ColorUtil.rgb(155,0,0));
		re.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.rgb(155,0,0)));
		refuel.setCommand(refuelc);
		commandmenu.add(refuel);
		refuel.setEnabled(false);
		refuel.getAllStyles().setBgColor(ColorUtil.rgb(155,0,0));
		refuel.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.rgb(155,0,0)));
		Button q = new MyButton("");
		q.setCommand(quit);
		commandmenu.add(q);
		
		//Hamburger Menu Setup
		this.getToolbar().addCommandToSideMenu(addasteroid);
		this.getToolbar().addCommandToSideMenu(addss);
		this.getToolbar().addCommandToSideMenu(addps);
		this.getToolbar().addCommandToSideMenu(reload);
		this.getToolbar().addCommandToSideMenu(tick);
		this.getToolbar().addCommandToSideMenu(quit);
		CheckBox sound = new CheckBox("");
		sound.setCommand(ts);
		sound.getAllStyles().setBgTransparency(255);
		sound.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		sound.setSelected(true);
		this.getToolbar().addComponentToSideMenu(sound);
		
		//Right Menu Setup
		this.getToolbar().addCommandToRightBar(neu);
		this.getToolbar().addCommandToRightBar(sav);
		this.getToolbar().addCommandToRightBar(und);
		this.getToolbar().addCommandToRightBar(about);
		this.getToolbar().addCommandToRightBar(quit);
		
		//KeyListener Setup
		this.addKeyListener(-91, accelerate);
		this.addKeyListener(-92, deccelerate);
		this.addKeyListener(-93, left);
		this.addKeyListener(-94, right);
		this.addKeyListener(44, leftml);
		this.addKeyListener(46, rightml);
		this.addKeyListener(-90, psfire);
		this.addKeyListener('j', jump);
		this.addKeyListener('Q', quit);
		
		//Screen Setup
		Toolbar header = this.getToolbar();
		header.setTitleCentered(true);
		header.setTitle("Asteroids Game");
		
		pv.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.LTGRAY));
		mv.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.BLACK));
		commandmenu.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.WHITE));
		commandmenu.getAllStyles().setPadding(Component.TOP,1);
		
		this.setLayout(new BorderLayout());
		this.add(BorderLayout.CENTER, mv);
		this.add(BorderLayout.NORTH, pv);
		this.add(BorderLayout.WEST,commandmenu);
		
		//Game Time Setup
		timer=new UITimer(this);
		timer.schedule(40, true, this);
		
		this.show();
		gw.init(mv.getWidth(),mv.getHeight());
	}
	
	public void run() {
		gw.tick();
	}
	public void pause() {
		timer.schedule(40, false, this);
		this.removeKeyListener(-91, keybindings.get(0));
		this.removeKeyListener(-92, keybindings.get(1));
		this.removeKeyListener(-93, keybindings.get(2));
		this.removeKeyListener(-94, keybindings.get(3));
		this.removeKeyListener(44, keybindings.get(4));
		this.removeKeyListener(46, keybindings.get(5));
		this.removeKeyListener(-90, keybindings.get(6));
		this.removeKeyListener('j', keybindings.get(7));
		this.removeKeyListener('Q', keybindings.get(8));
		for(int i=0;i<disable.size();i++) {
			disable.get(i).setEnabled(false);
			disable.get(i).getAllStyles().setBgColor(ColorUtil.rgb(155,0,0));
			disable.get(i).getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.rgb(155,0,0)));
		}
		p.setEnabled(false);
		p.getAllStyles().setBgColor(ColorUtil.rgb(155,0,0));
		p.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.rgb(155,0,0)));
		re.setEnabled(true);
		re.getAllStyles().setBgColor(ColorUtil.rgb(255,0,0));
		re.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.rgb(255,0,0)));
		refuel.setEnabled(true);
		refuel.getAllStyles().setBgColor(ColorUtil.rgb(255,0,0));
		refuel.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.rgb(255,0,0)));
		this.show();
	}
	public void resume() {
		p.setEnabled(true);
		p.getAllStyles().setBgColor(ColorUtil.rgb(255,0,0));
		p.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.rgb(255,0,0)));
		re.setEnabled(false);
		re.getAllStyles().setBgColor(ColorUtil.rgb(155,0,0));
		re.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.rgb(155,0,0)));
		refuel.setEnabled(false);
		refuel.getAllStyles().setBgColor(ColorUtil.rgb(155,0,0));
		refuel.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.rgb(155,0,0)));
		for(int i=0;i<disable.size();i++) {
			disable.get(i).setEnabled(true);
			disable.get(i).getAllStyles().setBgColor(ColorUtil.rgb(255,0,0));
			disable.get(i).getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.rgb(255,0,0)));
		}
		this.addKeyListener(-91, keybindings.get(0));
		this.addKeyListener(-92, keybindings.get(1));
		this.addKeyListener(-93, keybindings.get(2));
		this.addKeyListener(-94, keybindings.get(3));
		this.addKeyListener(44, keybindings.get(4));
		this.addKeyListener(46, keybindings.get(5));
		this.addKeyListener(-90, keybindings.get(6));
		this.addKeyListener('j', keybindings.get(7));
		this.addKeyListener('Q', keybindings.get(8));
		this.show();
		timer.schedule(40, true, this);
	}
	public void keyPressed(int code) {
        super.keyReleased(code);
    }
    public void keyReleased(int code) {
    }
}
